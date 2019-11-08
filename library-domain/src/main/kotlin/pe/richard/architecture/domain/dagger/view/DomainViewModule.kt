package pe.richard.architecture.domain.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.domain.dagger.view.test.DomainViewTest
import pe.richard.architecture.domain.dagger.view.test.IDomainViewTest

internal object DomainViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindDomainViewTest(inject: DomainViewTest): IDomainViewTest

    }

}