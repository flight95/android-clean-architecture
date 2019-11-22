package pe.richard.architecture.data.user

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import pe.richard.architecture.data.core.user.UserData

interface UserRepository {

    fun observe(id: String): Flowable<UserData>

    fun get(id: String): Single<UserData>

    fun getByEmail(email: String): Single<UserData>

    fun create(email: String): Completable

    fun update(user: UserData): Completable

    fun delete(id: String): Completable

}