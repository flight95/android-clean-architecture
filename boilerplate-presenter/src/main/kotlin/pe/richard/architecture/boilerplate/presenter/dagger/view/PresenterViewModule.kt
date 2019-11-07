package pe.richard.architecture.boilerplate.presenter.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.boilerplate.presenter.dagger.view.test.IPresenterViewTest
import pe.richard.architecture.boilerplate.presenter.dagger.view.test.PresenterViewTest
import pe.richard.architecture.core.dagger.scope.ViewScope

internal object PresenterViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindPresenterViewTest(presenterViewTest: PresenterViewTest): IPresenterViewTest

    }

}