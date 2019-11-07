package pe.richard.architecture.boilerplate.dagger.android

import android.os.Bundle
import pe.richard.architecture.boilerplate.dagger.activity.ActivityComponent
import pe.richard.architecture.core.view.navigation.NavActivity

abstract class DaggerActivity : NavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(getComponent())
        super.onCreate(savedInstanceState)
    }

    lateinit var activityComponent: ActivityComponent
        private set

    private fun getComponent(): ActivityComponent =
        when (this::activityComponent.isInitialized) {
            true -> activityComponent
            false -> (application as? DaggerApplication)
                ?.run {
                    applicationComponent
                        .activityComponentFactory()
                        .create(this@DaggerActivity)
                        .also { activityComponent = it }
                }
                ?: throw IllegalArgumentException("No ApplicationComponent was found for $application")
        }

    abstract fun inject(component: ActivityComponent)

}