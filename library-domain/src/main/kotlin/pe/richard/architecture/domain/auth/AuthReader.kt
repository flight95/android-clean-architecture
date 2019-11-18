package pe.richard.architecture.domain.auth

import io.reactivex.Flowable
import io.reactivex.Single
import pe.richard.architecture.data.core.auth.AuthData

interface AuthReader {

    fun getSync(): Single<AuthData>

    fun getStatic(): Single<AuthData>

    fun observeDynamic(): Flowable<AuthData>

}