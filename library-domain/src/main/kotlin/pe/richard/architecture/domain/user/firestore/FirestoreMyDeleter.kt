package pe.richard.architecture.domain.user.firestore

import io.reactivex.Completable
import pe.richard.architecture.data.user.MyRepository
import pe.richard.architecture.domain.user.MyDeleter
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreMyDeleter @Inject constructor(
    @Named("FirestoreMyRepository") private val repository: MyRepository
) : MyDeleter {

    override fun delete(): Completable = repository.delete()

}