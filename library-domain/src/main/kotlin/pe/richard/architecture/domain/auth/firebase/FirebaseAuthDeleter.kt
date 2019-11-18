package pe.richard.architecture.domain.auth.firebase

import io.reactivex.Completable
import pe.richard.architecture.data.auth.AuthRepository
import pe.richard.architecture.domain.auth.AuthDeleter
import javax.inject.Inject
import javax.inject.Named

internal class FirebaseAuthDeleter @Inject constructor(
    @Named("FirebaseAuthRepository") private val repository: AuthRepository
) : AuthDeleter {

    override fun deleteCache(): Completable =
        repository.deleteCache()

    override fun delete(): Completable =
        repository.delete()

}