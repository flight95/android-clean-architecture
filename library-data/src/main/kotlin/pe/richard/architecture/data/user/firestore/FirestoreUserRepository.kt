package pe.richard.architecture.data.user.firestore

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.core.user.UserDataSource
import pe.richard.architecture.data.user.UserRepository
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreUserRepository @Inject constructor(
    @Named("RemoteFirestoreUserDataSource") private val remote: UserDataSource
) : UserRepository {

    override fun observe(id: String): Flowable<UserData> =
        remote.observe(id)

    override fun get(id: String): Single<UserData> =
        remote.get(id)

    override fun getByEmail(email: String): Single<UserData> =
        remote.getByEmail(email)

    override fun create(email: String): Completable =
        remote.create(email)

    override fun update(user: UserData): Completable =
        remote.update(user)

    override fun delete(id: String): Completable =
        remote.delete(id)

}