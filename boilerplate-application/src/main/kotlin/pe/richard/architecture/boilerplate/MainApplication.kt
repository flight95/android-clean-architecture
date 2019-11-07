package pe.richard.architecture.boilerplate

import android.util.Log
import pe.richard.architecture.boilerplate.dagger.android.DaggerApplication
import pe.richard.architecture.boilerplate.dagger.application.ApplicationComponent
import pe.richard.architecture.boilerplate.dagger.application.test.IApplicationTest
import javax.inject.Inject

class MainApplication : DaggerApplication() {

    @Inject
    lateinit var applicationTest: IApplicationTest

    override fun onCreate() {
        super.onCreate()
        applicationTest.test()
    }

    //region DaggerApplication

    override fun inject(component: ApplicationComponent) {
        Log.e("MainApplication", "==================================================")
        Log.e("MainApplication", "application: $this")
        component.inject(this)
    }

    //endregion

}
