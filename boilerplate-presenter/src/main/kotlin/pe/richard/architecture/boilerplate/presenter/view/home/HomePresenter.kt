package pe.richard.architecture.boilerplate.presenter.view.home

import android.app.Application
import androidx.lifecycle.LiveData
import pe.richard.architecture.boilerplate.presenter.model.auth.Auth
import pe.richard.architecture.boilerplate.presenter.view.ErrorViewModel

abstract class HomePresenter(application: Application) :
    ErrorViewModel(application) {

    //region Auth

    abstract val auth: LiveData<Auth?>

    abstract fun getSyncAuth()

    abstract fun getStaticAuth()

    abstract fun observeDynamicAuth()

    abstract fun deleteCacheAuth()

    abstract fun deleteAuth()

    //endregion

}