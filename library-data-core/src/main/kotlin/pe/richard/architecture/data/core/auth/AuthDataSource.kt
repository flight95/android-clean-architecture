package pe.richard.architecture.data.core.auth

import io.reactivex.Completable
import io.reactivex.Single

interface AuthDataSource {

    fun get(): Single<AuthData>

    fun delete(): Completable

}