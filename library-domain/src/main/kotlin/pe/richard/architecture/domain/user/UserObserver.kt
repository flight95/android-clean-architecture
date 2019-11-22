package pe.richard.architecture.domain.user

import io.reactivex.Flowable
import pe.richard.architecture.data.core.user.UserData

interface UserObserver {

    fun observe(id: String): Flowable<UserData>

}