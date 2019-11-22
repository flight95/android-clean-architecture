package pe.richard.architecture.data.remote.dagger.application

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Binds
import dagger.Module
import dagger.Provides
import pe.richard.architecture.core.dagger.scope.ApplicationScope
import pe.richard.architecture.data.remote.dagger.application.test.IRemoteApplicationTest
import pe.richard.architecture.data.remote.dagger.application.test.RemoteApplicationTest

internal object RemoteApplicationModule {

    @Module
    class Provider {

        @Provides
        @ApplicationScope
        fun provideFirestore(): FirebaseFirestore =
            FirebaseFirestore.getInstance()
                .apply {
                    firestoreSettings =
                        FirebaseFirestoreSettings.Builder()
                            .setPersistenceEnabled(false) // Do not use persistence. Cache will be implemented directly.
                            .build()
                }

    }

    @Module
    abstract class Binder {

        @Binds
        @ApplicationScope
        abstract fun bindRemoteApplicationTest(inject: RemoteApplicationTest): IRemoteApplicationTest

    }

}