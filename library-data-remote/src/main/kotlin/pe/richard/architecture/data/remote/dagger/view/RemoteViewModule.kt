package pe.richard.architecture.data.remote.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.data.core.auth.AuthDataSource
import pe.richard.architecture.data.core.user.MyDataSource
import pe.richard.architecture.data.core.user.UserDataSource
import pe.richard.architecture.data.remote.auth.firebase.RemoteFirebaseAuthDataSource
import pe.richard.architecture.data.remote.dagger.view.test.IRemoteViewTest
import pe.richard.architecture.data.remote.dagger.view.test.RemoteViewTest
import pe.richard.architecture.data.remote.user.firestore.RemoteFirestoreMyDataSource
import pe.richard.architecture.data.remote.user.firestore.RemoteFirestoreUserDataSource
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
        @Named("RemoteFirestoreUserDataSource")
        abstract fun bindRemoteFirestoreUserDataSource(inject: RemoteFirestoreUserDataSource): UserDataSource

        @Binds
        @ViewScope
        @Named("RemoteFirestoreMyDataSource")
        abstract fun bindRemoteFirestoreMyDataSource(inject: RemoteFirestoreMyDataSource): MyDataSource

        @Binds
        @ViewScope
        abstract fun bindRemoteViewTest(inject: RemoteViewTest): IRemoteViewTest

    }

}