package pe.richard.architecture.domain.user.firestore

import io.reactivex.Flowable
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.user.UserRepository
import pe.richard.architecture.domain.user.UserObserver
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreUserObserver @Inject constructor(
    @Named("FirestoreUserRepository") private val repository: UserRepository
) : UserObserver {

    override fun observe(id: String): Flowable<UserData> = repository.observe(id)

}