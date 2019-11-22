package pe.richard.architecture.domain.user.firestore

import io.reactivex.Completable
import pe.richard.architecture.data.user.MyRepository
import pe.richard.architecture.domain.user.MyCreator
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreMyCreator @Inject constructor(
    @Named("FirestoreMyRepository") private val repository: MyRepository
) : MyCreator {

    override fun create(): Completable = repository.create()

}