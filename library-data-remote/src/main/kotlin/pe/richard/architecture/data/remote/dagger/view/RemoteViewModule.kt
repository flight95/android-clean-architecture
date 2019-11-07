package pe.richard.architecture.data.remote.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.data.remote.dagger.view.test.IRemoteViewTest
import pe.richard.architecture.data.remote.dagger.view.test.RemoteViewTest

internal object RemoteViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindRemoteViewTest(inject: RemoteViewTest): IRemoteViewTest

    }

}