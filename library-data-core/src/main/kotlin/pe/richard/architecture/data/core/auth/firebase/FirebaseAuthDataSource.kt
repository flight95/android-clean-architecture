package pe.richard.architecture.data.core.auth.firebase

import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pe.richard.architecture.core.error.AuthError
import pe.richard.architecture.data.core.auth.AuthDataSource

abstract class FirebaseAuthDataSource : AuthDataSource {

    protected fun getFirebase(): Single<FirebaseUser> =
        Single
            .create<FirebaseUser> { emitter ->
                try {
                    when (val account = FirebaseAuth.getInstance().currentUser) {
                        null -> emitter.onError(AuthError.GetFirebase())
                        else -> emitter.onSuccess(account)
                    }
                } catch (e: Throwable) {
                    emitter.onError(AuthError.GetFirebase(e))
                }
            }
            .subscribeOn(Schedulers.io())

    protected fun getGoogleClient(activity: FragmentActivity, firebaseClientId: String): Single<GoogleSignInClient> =
        Single
            .create<GoogleSignInClient> { emitter ->
                try {
                    emitter.onSuccess(
                        GoogleSignIn.getClient(
                            activity,
                            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(firebaseClientId)
                                .requestEmail()
                                .build()
                        )
                    )
                } catch (e: Throwable) {
                    emitter.onError(AuthError.GetGoogleClient(e))
                }
            }
            .subscribeOn(Schedulers.io())

    protected fun deleteFirebase(): Completable =
        Completable
            .create { emitter ->
                try {
                    FirebaseAuth.getInstance().signOut()
                    emitter.onComplete()
                } catch (e: Throwable) {
                    emitter.onError(AuthError.DeleteFirebase(e))
                }
            }
            .subscribeOn(Schedulers.io())

}