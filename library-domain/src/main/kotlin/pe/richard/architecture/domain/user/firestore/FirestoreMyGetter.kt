package pe.richard.architecture.domain.user.firestore

import io.reactivex.Single
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.user.MyRepository
import pe.richard.architecture.domain.user.MyGetter
import javax.inject.Inject
import javax.inject.Named

internal class FirestoreMyGetter @Inject constructor(
    @Named("FirestoreMyRepository") private val repository: MyRepository
) : MyGetter {

    override fun get(): Single<UserData> = repository.get()

}