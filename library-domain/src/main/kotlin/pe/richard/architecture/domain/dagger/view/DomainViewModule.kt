package pe.richard.architecture.domain.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.domain.auth.AuthDeleter
import pe.richard.architecture.domain.auth.AuthReader
import pe.richard.architecture.domain.auth.firebase.FirebaseAuthDeleter
import pe.richard.architecture.domain.auth.firebase.FirebaseAuthReader
import pe.richard.architecture.domain.dagger.view.test.DomainViewTest
import pe.richard.architecture.domain.dagger.view.test.IDomainViewTest
import javax.inject.Named

internal object DomainViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        @Named("FirebaseAuthReader")
        abstract fun bindFirebaseAuthReader(inject: FirebaseAuthReader): AuthReader

        @Binds
        @ViewScope
        @Named("FirebaseAuthDeleter")
        abstract fun bindFirebaseAuthDeleter(inject: FirebaseAuthDeleter): AuthDeleter

        @Binds
        @ViewScope
        abstract fun bindDomainViewTest(inject: DomainViewTest): IDomainViewTest

    }

}