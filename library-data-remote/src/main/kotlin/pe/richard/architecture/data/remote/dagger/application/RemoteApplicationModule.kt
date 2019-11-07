package pe.richard.architecture.data.remote.dagger.application

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ApplicationScope
import pe.richard.architecture.data.remote.dagger.application.test.IRemoteApplicationTest
import pe.richard.architecture.data.remote.dagger.application.test.RemoteApplicationTest

internal object RemoteApplicationModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ApplicationScope
        abstract fun bindRemoteApplicationTest(inject: RemoteApplicationTest): IRemoteApplicationTest

    }

}