package pe.richard.architecture.core.view.navigation

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import pe.richard.architecture.core.view.extensions.getBottomNavigationViewHeight
import pe.richard.architecture.core.view.extensions.getNavigationBarHeight

/**
 * Navigation Fragment in NavActivity.
 * Call initRootViewPadding, set root view padding from NavUI.
 *
 * {@code
 * override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
 *     inflater.inflate(R.layout.fragment_home, container, false)
 *         ?.also { view -> initRootViewPadding(view) }
 * }
 *
 * @see NavActivity
 * @see NavUI
 */
open class NavFragment : Fragment() {

    val previousDestination: NavDestination?
        get() = (context as? NavActivity)?.previousDestination

    val currentDestination: NavDestination?
        get() = (context as? NavActivity)?.currentDestination

    fun initRootViewPadding(root: View) {
        NavUI.create(root.context, arguments).let {
            root.setPadding(
                0,
                0,
                0,
                when {
                    it.enableBottomNavigation && it.enableBottomNavigationBehavior -> resources.getNavigationBarHeight()
                    it.enableBottomNavigationBehavior -> resources.getNavigationBarHeight()
                    it.enableBottomNavigation -> resources.getBottomNavigationViewHeight()
                    else -> resources.getNavigationBarHeight()
                }
            )
        }
    }

}