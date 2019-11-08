package pe.richard.architecture.boilerplate.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.boilerplate.dagger.view.test.IViewTest
import pe.richard.architecture.boilerplate.dagger.view.test.ViewTest
import pe.richard.architecture.core.dagger.scope.ViewScope

internal object ViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindViewTest(viewTest: ViewTest): IViewTest

    }

}