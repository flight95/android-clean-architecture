package pe.richard.architecture.core.lifecycle.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

var <T> MutableLiveData<T>.changed: T?
    get() = value
    set(new) = when (value == new) {
        true -> {
            // Do nothing
        }
        false -> value = new
    }

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (value: T) -> Unit) {
    removeObservers(owner) // Remove LiveData observers that is not yet removed.
    observe(owner, Observer { observer(it) })
}
