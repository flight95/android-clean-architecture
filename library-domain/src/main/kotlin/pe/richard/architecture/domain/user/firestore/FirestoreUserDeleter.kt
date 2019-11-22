package pe.richard.architecture.domain.user.firestore

import io.reactivex.Completable
import pe.richard.architecture.data.user.UserRepository
import pe.richard.architecture.domain.user.UserDeleter
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreUserDeleter @Inject constructor(
    @Named("FirestoreUserRepository") private val repository: UserRepository
) : UserDeleter {

    override fun delete(id: String): Completable = repository.delete(id)

}