package pe.richard.architecture.domain.user

import io.reactivex.Single
import pe.richard.architecture.data.core.user.UserData

interface UserGetter {

    fun get(id: String): Single<UserData>

    fun getByEmail(email: String): Single<UserData>

}