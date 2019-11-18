package pe.richard.architecture.boilerplate.presenter.dagger.view

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import pe.richard.architecture.boilerplate.presenter.dagger.view.test.IPresenterViewTest
import pe.richard.architecture.boilerplate.presenter.dagger.view.test.PresenterViewTest
import pe.richard.architecture.boilerplate.presenter.view.home.HomeViewModel
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.domain.auth.AuthDeleter
import pe.richard.architecture.domain.auth.AuthReader
import javax.inject.Named

internal object PresenterViewModule {

    @Module
    class Provider {

        @Provides
        @ViewScope
        @Named("HomePresenter.Factory")
        fun provideHomePresenterFactory(
            application: Application,
            @Named("FirebaseAuthReader") authReader: AuthReader,
            @Named("FirebaseAuthDeleter") authDeleter: AuthDeleter
        ): ViewModelProvider.Factory =
            HomeViewModel.Factory(application, authReader, authDeleter)

    }

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindPresenterViewTest(presenterViewTest: PresenterViewTest): IPresenterViewTest

    }

}