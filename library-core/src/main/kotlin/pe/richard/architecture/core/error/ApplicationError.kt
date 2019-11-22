package pe.richard.architecture.core.error

import android.content.Context
import androidx.annotation.StringRes
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pe.richard.architecture.core.R

open class ApplicationError(
    val code: Int,
    val type: Type,
    @StringRes private val resource: Int = type.resource,
    cause: Throwable? = null
) : Throwable(cause) {

    companion object {

        fun createCompletable(error: ApplicationError): Completable =
            Completable.create { emitter -> emitter.onError(error) }
                .subscribeOn(Schedulers.io())

        fun <T : Any> createSingle(error: ApplicationError): Single<T> =
            Single.create<T> { emitter -> emitter.onError(error) }
                .subscribeOn(Schedulers.io())

    }

    enum class Service {

        //region TODO: If you want to add another service, you have to add it here.

        Auth,
        User;

        //endregion

        val code = (ordinal + 1) * 100000

    }

    enum class Type {
        Unknown,
        Network,
        Transform,
        Get,
        Create,
        Update,
        Delete;

        val resource: Int
            @StringRes get() =
                when (this) {
                    Unknown -> R.string.error_unknown
                    Network -> R.string.error_network
                    Transform -> R.string.error_transform
                    Get -> R.string.error_read
                    Create -> R.string.error_create
                    Update -> R.string.error_update
                    Delete -> R.string.error_delete
                }

        val code: Int
            @StringRes get() =
                when (this) {
                    Unknown -> 1
                    Network -> 200
                    Transform -> 300
                    Get -> 400
                    Create -> 500
                    Update -> 600
                    Delete -> 700
                }
    }

    fun getDisplayMessage(context: Context?) = "[$code] ${context?.getString(resource)}"

}