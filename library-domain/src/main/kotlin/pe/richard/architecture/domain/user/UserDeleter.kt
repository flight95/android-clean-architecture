package pe.richard.architecture.domain.user

import io.reactivex.Completable

interface UserDeleter {

    fun delete(id: String): Completable

}