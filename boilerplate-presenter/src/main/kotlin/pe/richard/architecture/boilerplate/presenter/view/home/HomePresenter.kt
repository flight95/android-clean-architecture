package pe.richard.architecture.boilerplate.presenter.view.home

import android.app.Application
import androidx.lifecycle.LiveData
import pe.richard.architecture.boilerplate.presenter.model.auth.Auth
import pe.richard.architecture.boilerplate.presenter.model.user.User
import pe.richard.architecture.boilerplate.presenter.view.ErrorViewModel

abstract class HomePresenter(application: Application) :
    ErrorViewModel(application) {

    //region Auth

    abstract val auth: LiveData<Auth?>

    abstract fun observeDynamicAuth()

    abstract fun getSyncAuth()

    abstract fun getStaticAuth()

    abstract fun deleteCacheAuth()

    abstract fun deleteAuth()

    //endregion

    //region User

    abstract val user: LiveData<User?>

    abstract fun observeUser(id: String)

    abstract fun getUser(id: String)

    abstract fun getUserByEmail(email: String)

    abstract fun createUser(email: String)

    abstract fun updateUser(user: User)

    abstract fun deleteUser(id: String)

    //endregion

    //region My

    abstract val my: LiveData<User?>

    abstract fun getMy()

    abstract fun createMy()

    abstract fun updateMy(user: User)

    abstract fun deleteMy()

    //endregion

}