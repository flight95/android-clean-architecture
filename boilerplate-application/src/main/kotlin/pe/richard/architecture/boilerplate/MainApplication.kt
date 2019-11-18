package pe.richard.architecture.boilerplate

import pe.richard.architecture.boilerplate.dagger.android.DaggerApplication
import pe.richard.architecture.boilerplate.dagger.application.ApplicationComponent

class MainApplication : DaggerApplication() {

    //region DaggerApplication

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }

    //endregion

}
