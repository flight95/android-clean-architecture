package pe.richard.architecture.domain.user

import io.reactivex.Single
import pe.richard.architecture.data.core.user.UserData

interface MyGetter {

    fun get(): Single<UserData>

}