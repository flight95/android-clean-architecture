package pe.richard.architecture.boilerplate

import android.os.Bundle
import android.view.WindowManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import pe.richard.architecture.core.view.navigation.NavActivity

class MainActivity : NavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) initActivity()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initActivity()
    }

    private fun initWindow() {
        // Need to set styles.xml and themes.xml.
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun initActivity() {
        initBottomNavigationView()
        initToolbar()
        initTransientBottomLayout()
    }

    //region NavActivity

    override fun getNavHostFragment(): Int = R.id.navHostFragment

    override fun getAppBarLayout(): AppBarLayout = appBarLayout

    override fun getCollapsingToolbarLayout(): CollapsingToolbarLayout = collapsingToolbarLayout

    override fun getMaterialToolbar(): MaterialToolbar = materialToolbar

    override fun getTopLevelDestination(): Set<Int> =
        setOf(
            R.id.homeFragment,
            R.id.messageFragment,
            R.id.notificationFragment,
            R.id.profileFragment
        )

    override fun getPopupDestination(): Set<Int> = setOf()

    override fun getTransientBottomLayout(): CoordinatorLayout = transientBottomLayout

    override fun getFloatingActionButton(): FloatingActionButton = floatingActionButton

    override fun getBottomNavigationView(): BottomNavigationView = bottomNavigationView

    //endregion

}
