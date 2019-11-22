package pe.richard.architecture.data.remote.auth.firebase

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.error.AuthError
import pe.richard.architecture.core.rx.extensions.flatMap
import pe.richard.architecture.core.rx.extensions.flatMapCompletable
import pe.richard.architecture.core.view.request.RequestCode
import pe.richard.architecture.core.view.request.RequestFragment
import pe.richard.architecture.data.core.auth.AuthData
import pe.richard.architecture.data.core.auth.firebase.FirebaseAuthDataSource
import javax.inject.Inject
import javax.inject.Named

internal class RemoteFirebaseAuthDataSource @Inject constructor(
    private val activity: FragmentActivity,
    private val fragment: Fragment,
    @Named("firebaseClientId") private val firebaseClientId: String
) : FirebaseAuthDataSource() {

    override fun get(): Single<AuthData> =
        getGoogle()
            .flatMap<GoogleSignInAccount, GoogleSignInAccount>(Schedulers.io()) { account, emitter ->
                try {
                    FirebaseAuth.getInstance()
                        .signInWithCredential(GoogleAuthProvider.getCredential(account.idToken, null))
                        .addOnCompleteListener(activity) { task ->
                            when (task.isSuccessful) {
                                true -> emitter.onSuccess(account)
                                false -> emitter.onError(AuthError.GetFirebase())
                            }
                        }
                } catch (e: Throwable) {
                    emitter.onError(AuthError.GetFirebase(e))
                }
            }
            .observeOn(Schedulers.io())
            .flatMap(getFirebase()) { google, firebase -> Pair(firebase, google) }
            .compose(RemoteFirebaseAuthMapper.DataSingleTransformer(From.Remote))

    private fun getGoogle(): Single<GoogleSignInAccount> =
        getGoogleClient(activity, firebaseClientId)
            .flatMap<GoogleSignInClient, GoogleSignInAccount>(AndroidSchedulers.mainThread()) { client, emitter ->
                try {
                    when (fragment) {
                        is RequestFragment -> {
                            fragment.startActivityForResult(client.signInIntent, RequestCode.GoogleAuth) { _, data ->
                                try {
                                    GoogleSignIn
                                        .getSignedInAccountFromIntent(data)
                                        .getResult(ApiException::class.java)
                                        .let { account ->
                                            when (account) {
                                                null -> emitter.onError(AuthError.GetGoogle())
                                                else -> emitter.onSuccess(account)
                                            }
                                        }
                                } catch (e: ApiException) {
                                    emitter.onError(
                                        when (e.statusCode) {
                                            12501 -> AuthError.GetGoogleCancel(e)
                                            else -> AuthError.GetGoogle(e)
                                        }
                                    )
                                } catch (e: Throwable) {
                                    emitter.onError(AuthError.GetGoogle(e))
                                }
                            }
                        }
                        else -> emitter.onError(AuthError.GetGoogle())
                    }
                } catch (e: Throwable) {
                    emitter.onError(AuthError.GetGoogle(e))
                }
            }
            .observeOn(Schedulers.io())

    override fun delete(): Completable =
        deleteFirebase()
            .flatMap { deleteGoogle() }
            .observeOn(Schedulers.io())

    private fun deleteGoogle(): Completable =
        getGoogleClient(activity, firebaseClientId)
            .flatMapCompletable(AndroidSchedulers.mainThread()) { client, emitter ->
                try {
                    client.revokeAccess()
                        .addOnCompleteListener { task ->
                            when (task.isSuccessful) {
                                true -> emitter.onComplete()
                                false -> emitter.onError(AuthError.DeleteGoogle())
                            }
                        }
                } catch (e: Throwable) {
                    emitter.onError(AuthError.DeleteGoogle(e))
                }
            }
            .observeOn(Schedulers.io())

}