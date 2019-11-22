package pe.richard.architecture.domain.auth

import io.reactivex.Flowable
import pe.richard.architecture.data.core.auth.AuthData

interface AuthObserver {

    fun observeDynamic(): Flowable<AuthData>

}