package pe.richard.architecture.domain.dagger.activity

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ActivityScope
import pe.richard.architecture.domain.dagger.activity.test.DomainActivityTest
import pe.richard.architecture.domain.dagger.activity.test.IDomainActivityTest

internal object DomainActivityModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ActivityScope
        abstract fun bindDomainActivityTest(inject: DomainActivityTest): IDomainActivityTest

    }

}