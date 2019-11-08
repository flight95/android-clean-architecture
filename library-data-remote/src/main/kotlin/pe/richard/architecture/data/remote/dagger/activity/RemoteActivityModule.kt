package pe.richard.architecture.data.remote.dagger.activity

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ActivityScope
import pe.richard.architecture.data.remote.dagger.activity.test.IRemoteActivityTest
import pe.richard.architecture.data.remote.dagger.activity.test.RemoteActivityTest

internal object RemoteActivityModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ActivityScope
        abstract fun bindRemoteActivityTest(inject: RemoteActivityTest): IRemoteActivityTest

    }

}