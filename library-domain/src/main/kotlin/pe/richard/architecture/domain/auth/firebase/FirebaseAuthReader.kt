package pe.richard.architecture.domain.auth.firebase

import io.reactivex.Flowable
import io.reactivex.Single
import pe.richard.architecture.data.auth.AuthRepository
import pe.richard.architecture.data.core.auth.AuthData
import pe.richard.architecture.domain.auth.AuthReader
import javax.inject.Inject
import javax.inject.Named

internal class FirebaseAuthReader @Inject constructor(
    @Named("FirebaseAuthRepository") private val repository: AuthRepository
) : AuthReader {

    override fun getSync(): Single<AuthData> = repository.getSync()

    override fun getStatic(): Single<AuthData> = repository.getStatic()

    override fun observeDynamic(): Flowable<AuthData> = repository.observeDynamic()

}