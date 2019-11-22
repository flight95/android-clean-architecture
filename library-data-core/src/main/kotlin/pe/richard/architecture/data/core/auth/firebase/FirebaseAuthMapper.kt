package pe.richard.architecture.data.core.auth.firebase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single
import io.reactivex.SingleTransformer
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.error.AuthError
import pe.richard.architecture.data.core.auth.AuthData

object FirebaseAuthMapper {

    open class DataSingleTransformer(private val from: From) :
        SingleTransformer<Pair<FirebaseUser, GoogleSignInAccount>, AuthData> {

        override fun apply(upstream: Single<Pair<FirebaseUser, GoogleSignInAccount>>): Single<AuthData> =
            upstream.map { transform(from, it.first, it.second) }

    }

    private fun transform(from: From, firebase: FirebaseUser, google: GoogleSignInAccount): AuthData =
        AuthData(
            from,
            firebase.uid,
            google.email ?: throw AuthError.TransformEmail(),
            google.familyName ?: throw AuthError.TransformFamilyName(),
            google.givenName ?: throw AuthError.TransformGivenName(),
            google.photoUrl
        )

}