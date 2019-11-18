package pe.richard.architecture.data.auth.firebase

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.error.ApplicationError
import pe.richard.architecture.core.error.AuthError
import pe.richard.architecture.core.view.extensions.isNetworkConnected
import pe.richard.architecture.data.auth.AuthRepository
import pe.richard.architecture.data.core.auth.AuthData
import pe.richard.architecture.data.core.auth.AuthDataSource
import javax.inject.Inject
import javax.inject.Named

internal class FirebaseAuthRepository @Inject constructor(
    private val application: Application,
    @Named("RemoteFirebaseAuthDataSource") private val remote: AuthDataSource,
    @Named("CacheFirebaseAuthDataSource") private val cache: AuthDataSource
) : AuthRepository {

    override fun getSync(): Single<AuthData> =
        when (application.isNetworkConnected()) {
            true -> remote.get() // Case Google with Firebase api, it will be supported auto sync.
            false -> ApplicationError.createSingle(AuthError.Network())
        }

    override fun getStatic(): Single<AuthData> =
        cache.get()
            .onErrorResumeNext { getSync() }

    override fun observeDynamic(): Flowable<AuthData> =
        getStatic()
            .flatMapPublisher {
                Single.just(it).run {
                    when (it.from) {
                        From.Cache -> concatWith(getSync())
                        From.Remote -> toFlowable()
                    }
                }
            }

    override fun deleteCache(): Completable =
        cache.delete()

    override fun delete(): Completable =
        when (application.isNetworkConnected()) {
            true -> remote.delete()
            false -> ApplicationError.createCompletable(AuthError.Network())
        }

}