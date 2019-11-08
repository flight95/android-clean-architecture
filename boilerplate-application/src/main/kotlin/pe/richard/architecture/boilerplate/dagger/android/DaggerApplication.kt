package pe.richard.architecture.boilerplate.dagger.android

import android.app.Application
import pe.richard.architecture.boilerplate.dagger.application.ApplicationComponent
import pe.richard.architecture.boilerplate.dagger.application.DaggerApplicationComponent

abstract class DaggerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        inject(getComponent())
    }

    lateinit var applicationComponent: ApplicationComponent
        private set

    private fun getComponent(): ApplicationComponent =
        when (this::applicationComponent.isInitialized) {
            true -> applicationComponent
            false -> DaggerApplicationComponent
                .factory()
                .create(this)
                .also { applicationComponent = it }
        }

    abstract fun inject(component: ApplicationComponent)

}