package pe.richard.architecture.boilerplate.presenter.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxViewModel(
    application: Application,
    private val disposables: CompositeDisposable = CompositeDisposable()
) : AndroidViewModel(application) {

    fun addDisposable(disposable: Disposable) {
        if (!disposables.isDisposed) disposables.add(disposable)
    }

    override fun onCleared() {
        if (!disposables.isDisposed) disposables.dispose()
        super.onCleared()
    }

}