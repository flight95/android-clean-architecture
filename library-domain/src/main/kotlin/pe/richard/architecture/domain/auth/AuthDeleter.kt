package pe.richard.architecture.domain.auth

import io.reactivex.Completable

interface AuthDeleter {

    fun deleteCache(): Completable

    fun delete(): Completable

}