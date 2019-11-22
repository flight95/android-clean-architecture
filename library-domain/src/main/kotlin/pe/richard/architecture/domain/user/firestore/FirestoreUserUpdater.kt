package pe.richard.architecture.domain.user.firestore

import io.reactivex.Completable
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.user.UserRepository
import pe.richard.architecture.domain.user.UserUpdater
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreUserUpdater @Inject constructor(
    @Named("FirestoreUserRepository") private val repository: UserRepository
) : UserUpdater {

    override fun update(user: UserData): Completable = repository.update(user)

}