package pe.richard.architecture.boilerplate.dagger.application

import dagger.Binds
import dagger.Module
import pe.richard.architecture.boilerplate.dagger.activity.ActivityComponent
import pe.richard.architecture.boilerplate.dagger.application.test.ApplicationTest
import pe.richard.architecture.boilerplate.dagger.application.test.IApplicationTest
import pe.richard.architecture.core.dagger.scope.ApplicationScope

internal object ApplicationModule {

    @Module(subcomponents = [ActivityComponent::class])
    class Subcomponents

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ApplicationScope
        abstract fun bindApplicationTest(inject: ApplicationTest): IApplicationTest

    }

}