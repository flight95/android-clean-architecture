package pe.richard.architecture.data.core.auth.firebase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single
import io.reactivex.SingleTransformer
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.error.AuthError
import pe.richard.architecture.data.core.auth.AuthData

object FirebaseAuthMapper {

    open class SingleDataTransformer(private val from: From) :
        SingleTransformer<Pair<FirebaseUser, GoogleSignInAccount>, AuthData> {

        override fun apply(upstream: Single<Pair<FirebaseUser, GoogleSignInAccount>>): Single<AuthData> =
            upstream.map {
                AuthData(
                    from,
                    it.first.uid,
                    it.second.email ?: throw AuthError.TransformEmail(),
                    it.second.familyName ?: throw AuthError.TransformFamilyName(),
                    it.second.givenName ?: throw AuthError.TransformGivenName(),
                    it.second.photoUrl
                )
            }

    }

}