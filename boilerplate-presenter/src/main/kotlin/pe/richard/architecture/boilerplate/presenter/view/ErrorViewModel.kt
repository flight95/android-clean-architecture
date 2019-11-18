package pe.richard.architecture.boilerplate.presenter.view

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class ErrorViewModel(application: Application) :
    RxViewModel(application) {

    protected val errorLiveData = MutableLiveData<Throwable?>()

    val error: LiveData<Throwable?> = errorLiveData

    fun observeError(owner: LifecycleOwner, observer: (value: Throwable?) -> Unit) {
        error.removeObservers(owner) // Remove LiveData observers that is not yet removed.
        error.observe(owner, Observer {
            observer(it)
            if (it != null) errorLiveData.value = null // After error process, you need to initialize the error LiveData.
        })
    }

}