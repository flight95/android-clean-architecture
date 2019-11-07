package pe.richard.architecture.core.view.extensions

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

@Suppress("UNCHECKED_CAST")
fun <B : CoordinatorLayout.Behavior<out View>> View.getBehavior(): B? =
    ((layoutParams as? CoordinatorLayout.LayoutParams)?.behavior) as? B
