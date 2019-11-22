package pe.richard.architecture.domain.auth.firebase

import io.reactivex.Flowable
import pe.richard.architecture.data.auth.AuthRepository
import pe.richard.architecture.data.core.auth.AuthData
import pe.richard.architecture.domain.auth.AuthObserver
import javax.inject.Inject
import javax.inject.Named

internal class FirebaseAuthObserver @Inject constructor(
    @Named("FirebaseAuthRepository") private val repository: AuthRepository
) : AuthObserver {

    override fun observeDynamic(): Flowable<AuthData> = repository.observeDynamic()

}