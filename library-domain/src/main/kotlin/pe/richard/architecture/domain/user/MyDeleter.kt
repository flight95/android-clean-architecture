package pe.richard.architecture.domain.user

import io.reactivex.Completable

interface MyDeleter {

    fun delete(): Completable

}