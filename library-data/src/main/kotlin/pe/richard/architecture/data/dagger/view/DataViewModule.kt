package pe.richard.architecture.data.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.data.auth.AuthRepository
import pe.richard.architecture.data.auth.firebase.FirebaseAuthRepository
import pe.richard.architecture.data.dagger.view.test.DataViewTest
import pe.richard.architecture.data.dagger.view.test.IDataViewTest
import javax.inject.Named

internal object DataViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        @Named("FirebaseAuthRepository")
        abstract fun bindFirebaseAuthRepository(inject: FirebaseAuthRepository): AuthRepository

        @Binds
        @ViewScope
        abstract fun bindDataViewTest(inject: DataViewTest): IDataViewTest

    }

}