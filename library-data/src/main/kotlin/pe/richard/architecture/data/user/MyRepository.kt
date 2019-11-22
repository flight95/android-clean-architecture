package pe.richard.architecture.data.user

import io.reactivex.Completable
import io.reactivex.Single
import pe.richard.architecture.data.core.user.UserData

interface MyRepository {

    fun get(): Single<UserData>

    fun create(): Completable

    fun update(user: UserData): Completable

    fun delete(): Completable

}