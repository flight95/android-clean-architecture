package pe.richard.architecture.data.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.data.auth.AuthRepository
import pe.richard.architecture.data.auth.firebase.FirebaseAuthRepository
import pe.richard.architecture.data.dagger.view.test.DataViewTest
import pe.richard.architecture.data.dagger.view.test.IDataViewTest
import pe.richard.architecture.data.user.MyRepository
import pe.richard.architecture.data.user.UserRepository
import pe.richard.architecture.data.user.firestore.FirestoreMyRepository
import pe.richard.architecture.data.user.firestore.FirestoreUserRepository
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
        @Named("FirestoreUserRepository")
        abstract fun bindFirestoreUserRepository(inject: FirestoreUserRepository): UserRepository

        @Binds
        @ViewScope
        @Named("FirestoreMyRepository")
        abstract fun bindFirestoreMyRepository(inject: FirestoreMyRepository): MyRepository

        @Binds
        @ViewScope
        abstract fun bindDataViewTest(inject: DataViewTest): IDataViewTest

    }

}