package pe.richard.architecture.domain.user

import io.reactivex.Completable

interface UserCreator {

    fun create(email: String): Completable

}