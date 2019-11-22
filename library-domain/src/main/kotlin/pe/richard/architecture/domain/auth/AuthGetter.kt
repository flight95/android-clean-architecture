package pe.richard.architecture.domain.auth

import io.reactivex.Single
import pe.richard.architecture.data.core.auth.AuthData

interface AuthGetter {

    fun getSync(): Single<AuthData>

    fun getStatic(): Single<AuthData>

}