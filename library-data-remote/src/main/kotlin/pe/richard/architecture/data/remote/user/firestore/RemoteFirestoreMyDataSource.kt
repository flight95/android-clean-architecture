package pe.richard.architecture.data.remote.user.firestore

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.error.UserError
import pe.richard.architecture.core.rx.extensions.flatMap
import pe.richard.architecture.data.core.auth.AuthData
import pe.richard.architecture.data.core.user.MyDataSource
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.remote.firestore.RemoteFirestoreDataSource
import javax.inject.Inject

internal class RemoteFirestoreMyDataSource @Inject constructor(
    service: FirebaseFirestore
) : RemoteFirestoreDataSource(service), MyDataSource {

    override val collection: String = "users"

    override fun get(auth: Single<AuthData>): Single<UserData> =
        auth
            .flatMap { data ->
                getFirestore(
                    RemoteFirestoreUserMapper.Key.AuthId.value,
                    data.id,
                    UserError.GetFirestoreMyNone(),
                    UserError.GetFirestoreMyUnique(),
                    { UserError.GetFirestoreMy(it) }
                )
            }
            .observeOn(Schedulers.io())
            .compose(RemoteFirestoreUserMapper.DataSingleTransformer(From.Remote))

    // TODO: change Function from Firestore.
    override fun create(auth: Single<AuthData>): Completable =
        get(auth)
            .flatMapCompletable { Completable.error(UserError.CreateFirestoreMy()) }
            .onErrorResumeNext { e ->
                when (e) {
                    is UserError.GetFirestoreMyNone ->
                        createFirestore(
                            auth.map { data ->
                                mapOf( // TODO: When creating, use only email and authentication identifier.
                                    RemoteFirestoreUserMapper.Key.AuthId.value to data.id,
                                    RemoteFirestoreUserMapper.Key.Email.value to data.email
                                )
                            },
                            { UserError.CreateFirestoreMy(it) }
                        )
                    is UserError.CreateFirestoreMy -> Completable.error(e)
                    else -> Completable.error(UserError.CreateFirestoreMy(cause = e))
                }
            }

    // TODO: change Function from Firestore.
    override fun update(auth: Single<AuthData>, user: UserData): Completable =
        updateFirestore(
            Single.just(user)
                .compose(RemoteFirestoreUserMapper.RemoteSingleTransformer())
                .flatMap(auth) { map, data ->
                    // Fix email and authentication identifier from authentication.
                    map.toMutableMap().apply {
                        set(RemoteFirestoreUserMapper.Key.AuthId.value, data.id)
                        set(RemoteFirestoreUserMapper.Key.Email.value, data.email)
                    }.toMap()
                },
            { UserError.UpdateFirestoreMy(it) }
        )

    // TODO: change Function from Firestore.
    override fun delete(auth: Single<AuthData>): Completable =
        deleteFirestore(
            get(auth).compose(RemoteFirestoreUserMapper.RemoteSingleTransformer()),
            { UserError.DeleteFirestoreMy(it) }
        )

}