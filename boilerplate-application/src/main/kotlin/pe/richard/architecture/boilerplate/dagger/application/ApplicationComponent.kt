package pe.richard.architecture.boilerplate.dagger.application

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import pe.richard.architecture.boilerplate.MainApplication
import pe.richard.architecture.boilerplate.dagger.activity.ActivityComponent
import pe.richard.architecture.boilerplate.presenter.dagger.application.PresenterApplicationModules
import pe.richard.architecture.core.dagger.scope.ApplicationScope

@Component(
    modules = [
        ApplicationModule.Subcomponents::class,
        ApplicationModule.Provider::class,
        ApplicationModule.Binder::class,
        PresenterApplicationModules::class
    ]
)
@ApplicationScope
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

    fun activityComponentFactory(): ActivityComponent.Factory

    //region TODO: If you want to add another application class, you have to inject it here.

    fun inject(application: MainApplication)

    //endregion

}