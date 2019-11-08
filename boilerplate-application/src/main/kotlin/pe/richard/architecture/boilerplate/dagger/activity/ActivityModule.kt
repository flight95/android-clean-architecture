package pe.richard.architecture.boilerplate.dagger.activity

import dagger.Binds
import dagger.Module
import pe.richard.architecture.boilerplate.dagger.activity.test.ActivityTest
import pe.richard.architecture.boilerplate.dagger.activity.test.IActivityTest
import pe.richard.architecture.boilerplate.dagger.view.ViewComponent
import pe.richard.architecture.core.dagger.scope.ActivityScope

internal object ActivityModule {

    @Module(subcomponents = [ViewComponent::class])
    class Subcomponents

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ActivityScope
        abstract fun bindActivityTest(inject: ActivityTest): IActivityTest

    }

}