package pe.richard.architecture.boilerplate.presenter.view.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pe.richard.architecture.boilerplate.presenter.model.auth.Auth
import pe.richard.architecture.boilerplate.presenter.model.auth.AuthMapper
import pe.richard.architecture.core.lifecycle.extensions.changed
import pe.richard.architecture.domain.auth.AuthDeleter
import pe.richard.architecture.domain.auth.AuthReader

internal class HomeViewModel(
    application: Application,
    private val authReader: AuthReader,
    private val authDeleter: AuthDeleter
) : HomePresenter(application) {

    //region ViewModelProvider.Factory

    class Factory(
        private val application: Application,
        private val authReader: AuthReader,
        private val authDeleter: AuthDeleter
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(model: Class<T>): T =
            when (model.isAssignableFrom(HomePresenter::class.java)) {
                true -> HomeViewModel(
                    application,
                    authReader,
                    authDeleter
                ) as T
                false -> throw IllegalArgumentException("$model was not founded.")
            }

    }

    //endregion

    //region Auth

    private val authLiveData = MutableLiveData<Auth?>()

    override val auth: LiveData<Auth?> = authLiveData

    override fun getSyncAuth() {
        Log.e("HomeViewModel", "getSyncAuth")
        addDisposable(
            authReader.getSync()
                .subscribeOn(Schedulers.io())
                .compose(AuthMapper.DataSingleTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "getSyncAuth.subscribe: $it")
                        authLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "getSyncAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun getStaticAuth() {
        Log.e("HomeViewModel", "getStaticAuth")
        addDisposable(
            authReader.getStatic()
                .subscribeOn(Schedulers.io())
                .compose(AuthMapper.DataSingleTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "getStaticAuth.subscribe: $it")
                        authLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "getStaticAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun observeDynamicAuth() {
        Log.e("HomeViewModel", "observeDynamicAuth")
        addDisposable(
            authReader.observeDynamic()
                .subscribeOn(Schedulers.io())
                .compose(AuthMapper.DataFlowableTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "observeDynamicAuth.subscribe: $it")
                        authLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "observeDynamicAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun deleteCacheAuth() {
        Log.e("HomeViewModel", "deleteCacheAuth")
        addDisposable(
            authDeleter.deleteCache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "deleteCacheAuth.subscribe")
                        authLiveData.changed = null
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "deleteCacheAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun deleteAuth() {
        Log.e("HomeViewModel", "deleteAuth")
        addDisposable(
            authDeleter.delete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "deleteAuth.subscribe")
                        authLiveData.changed = null
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "deleteAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    //endregion

}