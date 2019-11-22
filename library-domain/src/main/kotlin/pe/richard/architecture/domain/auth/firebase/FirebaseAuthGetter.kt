package pe.richard.architecture.domain.auth.firebase

import io.reactivex.Single
import pe.richard.architecture.data.auth.AuthRepository
import pe.richard.architecture.data.core.auth.AuthData
import pe.richard.architecture.domain.auth.AuthGetter
import javax.inject.Inject
import javax.inject.Named

internal class FirebaseAuthGetter @Inject constructor(
    @Named("FirebaseAuthRepository") private val repository: AuthRepository
) : AuthGetter {

    override fun getSync(): Single<AuthData> = repository.getSync()

    override fun getStatic(): Single<AuthData> = repository.getStatic()

}