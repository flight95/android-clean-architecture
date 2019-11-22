package pe.richard.architecture.domain.user.firestore

import io.reactivex.Single
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.user.UserRepository
import pe.richard.architecture.domain.user.UserGetter
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreUserGetter @Inject constructor(
    @Named("FirestoreUserRepository") private val repository: UserRepository
) : UserGetter {

    override fun get(id: String): Single<UserData> = repository.get(id)

    override fun getByEmail(email: String): Single<UserData> = repository.getByEmail(email)

}