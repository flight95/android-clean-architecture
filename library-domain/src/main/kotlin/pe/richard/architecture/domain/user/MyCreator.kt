package pe.richard.architecture.domain.user

import io.reactivex.Completable

interface MyCreator {

    fun create(): Completable

}