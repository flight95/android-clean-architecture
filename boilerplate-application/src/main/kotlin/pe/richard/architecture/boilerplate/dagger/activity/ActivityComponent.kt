package pe.richard.architecture.boilerplate.dagger.activity

import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Subcomponent
import pe.richard.architecture.boilerplate.MainActivity
import pe.richard.architecture.boilerplate.dagger.view.ViewComponent
import pe.richard.architecture.boilerplate.presenter.dagger.activity.PresenterActivityModules
import pe.richard.architecture.core.dagger.scope.ActivityScope

@Subcomponent(
    modules = [
        ActivityModule.Subcomponents::class,
        ActivityModule.Provider::class,
        ActivityModule.Binder::class,
        PresenterActivityModules::class
    ]
)
@ActivityScope
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: FragmentActivity): ActivityComponent
    }

    fun viewComponentFactory(): ViewComponent.Factory

    //region TODO: If you want to add another activity class, you have to inject it here.

    fun inject(activity: MainActivity)

    //endregion

}