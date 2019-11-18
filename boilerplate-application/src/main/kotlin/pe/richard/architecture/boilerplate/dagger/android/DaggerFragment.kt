package pe.richard.architecture.boilerplate.dagger.android

import android.content.Context
import pe.richard.architecture.boilerplate.dagger.view.ViewComponent
import pe.richard.architecture.boilerplate.view.NavGraphFragment

abstract class DaggerFragment : NavGraphFragment() {

    override fun onAttach(context: Context) {
        inject(getComponent())
        super.onAttach(context)
    }

    private fun getComponent(): ViewComponent =
        (activity as? DaggerActivity)
            ?.run {
                activityComponent
                    .viewComponentFactory()
                    .create(this@DaggerFragment)
            }
            ?: throw IllegalArgumentException("No ActivityComponent was found for $activity")

    abstract fun inject(component: ViewComponent)

}