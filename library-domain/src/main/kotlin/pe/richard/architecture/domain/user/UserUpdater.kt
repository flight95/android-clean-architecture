package pe.richard.architecture.domain.user

import io.reactivex.Completable
import pe.richard.architecture.data.core.user.UserData

interface UserUpdater {

    fun update(user: UserData): Completable
    
}