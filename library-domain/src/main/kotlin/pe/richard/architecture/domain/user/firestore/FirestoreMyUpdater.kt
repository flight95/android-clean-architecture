package pe.richard.architecture.domain.user.firestore

import io.reactivex.Completable
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.user.MyRepository
import pe.richard.architecture.domain.user.MyUpdater
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreMyUpdater @Inject constructor(
    @Named("FirestoreMyRepository") private val repository: MyRepository
) : MyUpdater {

    override fun update(user: UserData): Completable = repository.update(user)

}