package pe.richard.architecture.boilerplate.view

import pe.richard.architecture.boilerplate.R
import pe.richard.architecture.core.view.lifecycle.LifecycleFragment

abstract class NavGraphFragment : LifecycleFragment() {

    override fun getNavGraphId(): Int = R.id.nav_graph

}