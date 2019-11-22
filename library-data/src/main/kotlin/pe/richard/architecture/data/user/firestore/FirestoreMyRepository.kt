package pe.richard.architecture.data.user.firestore

import io.reactivex.Completable
import io.reactivex.Single
import pe.richard.architecture.data.auth.AuthRepository
import pe.richard.architecture.data.core.user.MyDataSource
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.user.MyRepository
import javax.inject.Inject
import javax.inject.Named

class FirestoreMyRepository @Inject constructor(
    @Named("FirebaseAuthRepository") private val auth: AuthRepository,
    @Named("RemoteFirestoreMyDataSource") private val remote: MyDataSource
) : MyRepository {

    override fun get(): Single<UserData> =
        remote.get(auth.getStatic())

    override fun create(): Completable =
        remote.create(auth.getStatic())

    override fun update(user: UserData): Completable =
        remote.update(auth.getStatic(), user)

    override fun delete(): Completable =
        remote.delete(auth.getStatic())

}