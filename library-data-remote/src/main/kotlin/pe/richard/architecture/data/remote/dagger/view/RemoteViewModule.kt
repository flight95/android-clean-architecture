package pe.richard.architecture.data.remote.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.data.core.auth.AuthDataSource
import pe.richard.architecture.data.remote.auth.firebase.RemoteFirebaseAuthDataSource
import pe.richard.architecture.data.remote.dagger.view.test.IRemoteViewTest
import pe.richard.architecture.data.remote.dagger.view.test.RemoteViewTest
import javax.inject.Named

internal object RemoteViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        @Named("RemoteFirebaseAuthDataSource")
        abstract fun bindRemoteFirebaseAuthDataSource(inject: RemoteFirebaseAuthDataSource): AuthDataSource

        @Binds
        @ViewScope
        abstract fun bindRemoteViewTest(inject: RemoteViewTest): IRemoteViewTest

    }

}