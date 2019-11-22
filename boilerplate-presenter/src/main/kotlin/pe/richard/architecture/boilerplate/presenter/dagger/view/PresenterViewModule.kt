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
import pe.richard.architecture.domain.auth.AuthGetter
import pe.richard.architecture.domain.auth.AuthObserver
import pe.richard.architecture.domain.user.MyCreator
import pe.richard.architecture.domain.user.MyDeleter
import pe.richard.architecture.domain.user.MyGetter
import pe.richard.architecture.domain.user.MyUpdater
import pe.richard.architecture.domain.user.UserCreator
import pe.richard.architecture.domain.user.UserDeleter
import pe.richard.architecture.domain.user.UserGetter
import pe.richard.architecture.domain.user.UserObserver
import pe.richard.architecture.domain.user.UserUpdater
import javax.inject.Named

internal object PresenterViewModule {

    @Module
    class Provider {

        @Provides
        @ViewScope
        @Named("HomePresenter.Factory")
        fun provideHomePresenterFactory(
            application: Application,
            @Named("FirebaseAuthGetter") authGetter: AuthGetter,
            @Named("FirebaseAuthObserver") authObserver: AuthObserver,
            @Named("FirebaseAuthDeleter") authDeleter: AuthDeleter,
            @Named("FirestoreUserObserver") userObserver: UserObserver,
            @Named("FirestoreUserGetter") userGetter: UserGetter,
            @Named("FirestoreUserCreator") userCreator: UserCreator,
            @Named("FirestoreUserUpdater") userUpdater: UserUpdater,
            @Named("FirestoreUserDeleter") userDeleter: UserDeleter,
            @Named("FirestoreMyGetter") myGetter: MyGetter,
            @Named("FirestoreMyCreator") myCreator: MyCreator,
            @Named("FirestoreMyUpdater") myUpdater: MyUpdater,
            @Named("FirestoreMyDeleter") myDeleter: MyDeleter
        ): ViewModelProvider.Factory =
            HomeViewModel.Factory(
                application,
                authObserver,
                authGetter,
                authDeleter,
                userObserver,
                userGetter,
                userCreator,
                userUpdater,
                userDeleter,
                myGetter,
                myCreator,
                myUpdater,
                myDeleter
            )

    }

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindPresenterViewTest(presenterViewTest: PresenterViewTest): IPresenterViewTest

    }

}