package pe.richard.architecture.boilerplate.dagger.view

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import pe.richard.architecture.boilerplate.presenter.dagger.view.PresenterViewModules
import pe.richard.architecture.boilerplate.view.home.HomeFragment
import pe.richard.architecture.boilerplate.view.message.MessageFragment
import pe.richard.architecture.boilerplate.view.notification.NotificationFragment
import pe.richard.architecture.boilerplate.view.profile.ProfileFragment
import pe.richard.architecture.core.dagger.scope.ViewScope

@Subcomponent(
    modules = [
        ViewModule.Provider::class,
        ViewModule.Binder::class,
        PresenterViewModules::class
    ]
)
@ViewScope
interface ViewComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): ViewComponent
    }

    //region TODO: If you want to add another fragment class, you have to inject it here.

    fun inject(fragment: HomeFragment)
    fun inject(fragment: MessageFragment)
    fun inject(fragment: NotificationFragment)
    fun inject(fragment: ProfileFragment)

    //endregion

}