package pe.richard.architecture.domain.dagger.application

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ApplicationScope
import pe.richard.architecture.domain.dagger.application.test.DomainApplicationTest
import pe.richard.architecture.domain.dagger.application.test.IDomainApplicationTest

internal object DomainApplicationModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ApplicationScope
        abstract fun bindDomainApplicationTest(inject: DomainApplicationTest): IDomainApplicationTest

    }

}