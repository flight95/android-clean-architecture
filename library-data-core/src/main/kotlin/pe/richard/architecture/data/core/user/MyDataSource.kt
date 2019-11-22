package pe.richard.architecture.data.core.user

import io.reactivex.Completable
import io.reactivex.Single
import pe.richard.architecture.data.core.auth.AuthData

interface MyDataSource {

    fun get(auth: Single<AuthData>): Single<UserData>

    fun create(auth: Single<AuthData>): Completable

    fun update(auth: Single<AuthData>, user: UserData): Completable

    fun delete(auth: Single<AuthData>): Completable

}