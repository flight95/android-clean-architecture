package pe.richard.architecture.data.remote.user.firestore

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.error.UserError
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.core.user.UserDataSource
import pe.richard.architecture.data.remote.firestore.RemoteFirestoreDataSource
import pe.richard.architecture.data.remote.firestore.setIdentifier
import javax.inject.Inject

internal class RemoteFirestoreUserDataSource @Inject constructor(
    service: FirebaseFirestore
) : RemoteFirestoreDataSource(service), UserDataSource {

    override val collection: String = "users"

    override fun observe(id: String): Flowable<UserData> =
        observeFirestore(
            id,
            UserError.ObserveFirestoreNone(),
            { UserError.ObserveFirestore(it) }
        )
            .observeOn(Schedulers.io())
            .compose(RemoteFirestoreUserMapper.DataFlowableTransformer(From.Remote))

    override fun get(id: String): Single<UserData> =
        getFirestore(
            id,
            UserError.GetFirestoreNone(),
            { UserError.GetFirestore(cause = it) }
        )
            .observeOn(Schedulers.io())
            .compose(RemoteFirestoreUserMapper.DataSingleTransformer(From.Remote))

    override fun getByEmail(email: String): Single<UserData> =
        getFirestore(
            RemoteFirestoreUserMapper.Key.Email.value,
            email,
            UserError.GetFirestoreNone(),
            UserError.GetFirestoreUnique(),
            { UserError.GetFirestore(cause = it) }
        )
            .observeOn(Schedulers.io())
            .compose(RemoteFirestoreUserMapper.DataSingleTransformer(From.Remote))

    // TODO: change Function from Firestore.
    override fun create(email: String): Completable =
        getByEmail(email)
            .flatMapCompletable { Completable.error(UserError.CreateFirestore()) }
            .onErrorResumeNext { e ->
                when (e) {
                    is UserError.GetFirestoreNone ->
                        createFirestore(
                            Single.just(
                                mapOf( // TODO: When creating, use only email.
                                    RemoteFirestoreUserMapper.Key.Email.value to email
                                )
                            ),
                            { UserError.CreateFirestore(cause = it) }
                        )
                    is UserError.CreateFirestore -> Completable.error(e)
                    else -> Completable.error(UserError.CreateFirestore(cause = e))
                }
            }

    // TODO: change Function from Firestore.
    override fun update(user: UserData): Completable =
        getByEmail(user.email)
            .flatMapCompletable { Completable.error(UserError.UpdateFirestore()) }
            .onErrorResumeNext { e ->
                when (e) {
                    is UserError.GetFirestoreNone ->
                        updateFirestore(
                            Single.just(
                                mapOf( // TODO: When updating, use only email.
                                    RemoteFirestoreUserMapper.Key.Email.value to user.email
                                ).setIdentifier(user.id)
                            ),
                            { UserError.UpdateFirestore(cause = it) }
                        )
                    is UserError.UpdateFirestore -> Completable.error(e)
                    else -> Completable.error(UserError.CreateFirestore(cause = e))
                }
            }

    // TODO: change Function from Firestore.
    override fun delete(id: String): Completable =
        getFirestore(
            id,
            UserError.GetFirestoreNone(),
            { UserError.GetFirestore(cause = it) }
        )
            .flatMapCompletable { data ->
                when (data[RemoteFirestoreUserMapper.Key.AuthId.value]) {
                    null -> deleteFirestore(id, { UserError.DeleteFirestore(cause = it) })
                    else -> Completable.error(UserError.DeleteFirestoreAuthentication())
                }
            }
            .observeOn(Schedulers.io())

}