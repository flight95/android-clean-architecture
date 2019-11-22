package pe.richard.architecture.domain.user.firestore

import io.reactivex.Completable
import pe.richard.architecture.data.user.UserRepository
import pe.richard.architecture.domain.user.UserCreator
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreUserCreator @Inject constructor(
    @Named("FirestoreUserRepository") private val repository: UserRepository
) : UserCreator {

    override fun create(email: String): Completable = repository.create(email)

}