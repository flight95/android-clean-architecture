package pe.richard.architecture.boilerplate.dagger.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.Provides
import pe.richard.architecture.boilerplate.dagger.view.test.IViewTest
import pe.richard.architecture.boilerplate.dagger.view.test.ViewTest
import pe.richard.architecture.boilerplate.presenter.view.home.HomePresenter
import pe.richard.architecture.core.dagger.scope.ViewScope
import javax.inject.Named

internal object ViewModule {

    @Module
    class Provider {

        @Provides
        @ViewScope
        fun provideHomePresenter(
            fragment: Fragment,
            @Named("HomePresenter.Factory") factory: ViewModelProvider.Factory
        ): HomePresenter =
            fragment.activity?.application?.run {
                ViewModelProviders
                    .of(fragment, factory)
                    .get(HomePresenter::class.java)
            } ?: throw IllegalArgumentException("Application is not valid.")

    }

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindViewTest(viewTest: ViewTest): IViewTest

    }

}