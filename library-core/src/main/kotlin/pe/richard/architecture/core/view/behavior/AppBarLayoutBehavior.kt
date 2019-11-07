package pe.richard.architecture.core.view.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class AppBarLayoutBehavior(context: Context, attrs: AttributeSet? = null) :
    AppBarLayout.Behavior(context, attrs) {

    var enabled: Boolean = true

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean =
        enabled && super.onStartNestedScroll(
            parent,
            child,
            directTargetChild,
            target,
            nestedScrollAxes,
            type
        )

}