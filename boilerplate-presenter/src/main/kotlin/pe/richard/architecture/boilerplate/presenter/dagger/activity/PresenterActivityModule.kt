package pe.richard.architecture.boilerplate.presenter.dagger.activity

import dagger.Binds
import dagger.Module
import pe.richard.architecture.boilerplate.presenter.dagger.activity.test.IPresenterActivityTest
import pe.richard.architecture.boilerplate.presenter.dagger.activity.test.PresenterActivityTest
import pe.richard.architecture.core.dagger.scope.ActivityScope

internal object PresenterActivityModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ActivityScope
        abstract fun bindPresenterActivityTest(inject: PresenterActivityTest): IPresenterActivityTest

    }

}