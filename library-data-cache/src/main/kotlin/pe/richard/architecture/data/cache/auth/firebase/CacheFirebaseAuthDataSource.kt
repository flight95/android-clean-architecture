package pe.richard.architecture.data.cache.auth.firebase

import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.error.AuthError
import pe.richard.architecture.core.rx.extensions.flatMap
import pe.richard.architecture.core.rx.extensions.flatMapCompletable
import pe.richard.architecture.data.core.auth.AuthData
import pe.richard.architecture.data.core.auth.firebase.FirebaseAuthDataSource
import javax.inject.Inject
import javax.inject.Named

internal class CacheFirebaseAuthDataSource @Inject constructor(
    private val activity: FragmentActivity,
    @Named("firebaseClientId") private val firebaseClientId: String
) : FirebaseAuthDataSource() {

    override fun get(): Single<AuthData> =
        getFirebase()
            .observeOn(Schedulers.io())
            .flatMap(getGoogle()) { firebase, google -> Pair(firebase, google) }
            .compose(CacheFirebaseAuthMapper.SingleDataTransformer(From.Cache))

    private fun getGoogle(): Single<GoogleSignInAccount> =
        Single
            .create<GoogleSignInAccount> { emitter ->
                try {
                    when (val account = GoogleSignIn.getLastSignedInAccount(activity)) {
                        null -> emitter.onError(AuthError.ReadGoogle())
                        else -> emitter.onSuccess(account)
                    }
                } catch (e: Throwable) {
                    emitter.onError(AuthError.ReadGoogle(e))
                }
            }
            .subscribeOn(Schedulers.io())

    override fun delete(): Completable =
        deleteFirebase()
            .flatMap { deleteGoogle() }
            .observeOn(Schedulers.io())

    private fun deleteGoogle(): Completable =
        getGoogleClient(activity, firebaseClientId)
            .flatMapCompletable(Schedulers.io()) { client, emitter ->
                try {
                    client.signOut()
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