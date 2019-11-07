package pe.richard.architecture.core.view.navigation

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pe.richard.architecture.core.view.behavior.AppBarLayoutBehavior
import pe.richard.architecture.core.view.behavior.BottomViewBehavior
import pe.richard.architecture.core.view.extensions.*

/**
 * Navigation Activity included NavHostFragment, AppBarLayout, FloatingActionButton, and BottomNavigationView.
 * Included Fragment must extended NavFragment.
 * NavActivity will be support NavController.OnDestinationChangedListener.
 * Call initToolbar(), initTransientBottomLayout(), and initBottomNavigationView() for setting NavController.
 *
 * @see NavFragment
 * @see NavController.OnDestinationChangedListener
 * @see initToolbar()
 * @see initTransientBottomLayout()
 * @see initBottomNavigationView()
 */
abstract class NavActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController

    var previousDestination: NavDestination? = null
        private set

    var currentDestination: NavDestination? = null
        private set

    private fun getNavController(): NavController =
        when (this::navController.isInitialized) {
            true -> navController
            false -> findNavController(getNavHostFragment())
                .also { navController = it }
                .apply {
                    removeOnDestinationChangedListener(this@NavActivity)
                    addOnDestinationChangedListener(this@NavActivity)
                }
        }

    @IdRes
    abstract fun getNavHostFragment(): Int

    override fun onSupportNavigateUp(): Boolean = getNavController().navigateUp()

    //region AppBarLayout

    /**
     * Get AppBarLayout.
     * AppBarLayout must have AppBarLayoutBehavior.
     *
     * @see AppBarLayoutBehavior
     * @return AppBarLayout
     */
    abstract fun getAppBarLayout(): AppBarLayout

    abstract fun getCollapsingToolbarLayout(): CollapsingToolbarLayout

    abstract fun getMaterialToolbar(): MaterialToolbar

    /**
     * Get top level destination.
     * In general, it is similar to menu in BottomNavigationView.
     * Must be a Set with @IdRes in nav_graph.xml.
     *
     * @return Top level destination.
     */
    abstract fun getTopLevelDestination(): Set<Int>

    /**
     * Get popup destination.
     * Similar to DialogFragment but different, It behaves like a popup.
     * Must be a Set with @IdRes in nav_graph.xml.
     *
     * @return Popup destination.
     */
    abstract fun getPopupDestination(): Set<Int>

    fun initToolbar() {
        getAppBarLayout().updateLayoutParams {
            height = (windowManager.defaultDisplay.getSize().x * 0.5).toInt()
        }
        with(getMaterialToolbar()) {
            updateLayoutParams<ViewGroup.MarginLayoutParams> {
                height = titleMarginTop + resources.getActionBarHeight(theme)
                topMargin = resources.getStatusBarHeight()
            }
            setSupportActionBar(this)
            getCollapsingToolbarLayout().setupWithNavController(
                this,
                getNavController(),
                AppBarConfiguration.Builder(getTopLevelDestination().union(getPopupDestination())).build()
            )
        }
    }

    private fun changeAppBarLayout(enabled: Boolean) {
        with(getAppBarLayout()) {
            setExpanded(enabled, true)
            getBehavior<AppBarLayoutBehavior>()?.enabled = enabled
        }
    }

    //endregion

    //region BottomNavigationView

    abstract fun getBottomNavigationView(): BottomNavigationView

    fun initBottomNavigationView() {
        with(getBottomNavigationView()) {
            updateLayoutParams {
                height = resources.getBottomNavigationViewHeight()
            }
            setPadding(0, 0, 0, resources.getNavigationBarHeight())
            setupWithNavController(getNavController())
        }
    }

    private fun changeBottomNavigationView(enabledView: Boolean, enabledBehavior: Boolean) {
        with(getBottomNavigationView()) view@{
            getBehavior<BottomViewBehavior<BottomNavigationView>>()?.apply {
                enabled = enabledBehavior
                when (enabledView) {
                    true -> slideUp(this@view)
                    false -> slideDown(this@view)
                }
            }
        }
    }

    //endregion

    //region FloatingActionButton

    /**
     * TransientBottomLayout have FloatingActionButton, and this layout located above BottomNavigationView.
     * {@code
     * <androidx.coordinatorlayout.widget.CoordinatorLayout
     *     android:id="@+id/transientBottomLayout"
     *     android:layout_width="match_parent"
     *     android:layout_height="@dimen/transient_bottom_layout_height"
     *     android:layout_gravity="top"
     *     app:layout_anchor="@id/bottomNavigationView"
     *     app:layout_anchorGravity="top|end">
     *
     *     <com.google.android.material.floatingactionbutton.FloatingActionButton
     *         android:id="@+id/floatingActionButton"
     *         android:layout_width="wrap_content"
     *         android:layout_height="wrap_content"
     *         android:layout_gravity="bottom|end"
     *         android:layout_marginStart="@dimen/activity_horizontal_margin"
     *         android:layout_marginEnd="@dimen/activity_horizontal_margin"
     *         android:layout_marginBottom="@dimen/activity_vertical_margin"
     *         app:srcCompat="@drawable/ic_add" />
     * </androidx.coordinatorlayout.widget.CoordinatorLayout>
     * }
     */
    abstract fun getTransientBottomLayout(): CoordinatorLayout

    abstract fun getFloatingActionButton(): FloatingActionButton

    fun initTransientBottomLayout() {
        getTransientBottomLayout().updateLayoutParams<ViewGroup.MarginLayoutParams> {
            bottomMargin = resources.getNavigationBarHeight()
        }
    }

    private fun changeFloatingActionButton(enabled: Boolean) {
        with(getFloatingActionButton()) {
            when (enabled) {
                true -> show()
                false -> hide()
            }
            setOnClickListener(null)
        }
    }

    //endregion

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        previousDestination = currentDestination
        onDestinationChanged(
            controller,
            previousDestination,
            destination,
            arguments
        )
        currentDestination = destination
    }

    open fun onDestinationChanged(
        controller: NavController,
        prevDestination: NavDestination?,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        NavUI.create(this, arguments).let {
            changeAppBarLayout(it.enableAppBar)
            changeBottomNavigationView(it.enableBottomNavigation, it.enableBottomNavigationBehavior)
            changeFloatingActionButton(it.enableFloatingActionButton)
        }
    }

}