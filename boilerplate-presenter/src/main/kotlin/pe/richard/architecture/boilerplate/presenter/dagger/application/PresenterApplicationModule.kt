package pe.richard.architecture.boilerplate.presenter.dagger.application

import dagger.Binds
import dagger.Module
import pe.richard.architecture.boilerplate.presenter.dagger.application.test.IPresenterApplicationTest
import pe.richard.architecture.boilerplate.presenter.dagger.application.test.PresenterApplicationTest
import pe.richard.architecture.core.dagger.scope.ApplicationScope

internal object PresenterApplicationModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ApplicationScope
        abstract fun bindPresenterApplicationTest(inject: PresenterApplicationTest): IPresenterApplicationTest

    }

}