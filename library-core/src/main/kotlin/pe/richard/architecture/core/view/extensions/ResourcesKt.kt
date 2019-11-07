package pe.richard.architecture.core.view.extensions

import android.annotation.SuppressLint
import android.content.res.Resources
import pe.richard.architecture.core.R

fun Resources.getStatusBarHeight(): Int =
    getDimensionPixelSize("status_bar_height") ?: 0

fun Resources.getActionBarHeight(theme: Resources.Theme?): Int =
    when (theme) {
        null -> 0
        else -> {
            theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
                .run {
                    val height = getDimension(0, 0f).toInt()
                    recycle()
                    height
                }
        }
    }

/**
 * Get default height for BottomNavigationView.
 * It used private resource from com.google.android.material:material library.
 *
 * @return Default height for BottomNavigationView.
 */
@SuppressLint("PrivateResource")
fun Resources.getBottomNavigationViewHeight(): Int =
    getDimensionPixelSize(R.dimen.design_bottom_navigation_height) + getNavigationBarHeight()

fun Resources.getNavigationBarHeight(): Int =
    getDimensionPixelSize("navigation_bar_height") ?: 0

fun Resources.getDimensionPixelSize(name: String): Int? {
    val id = getIdentifier(name, "dimen", "android")
    return if (id > 0) getDimensionPixelSize(id) else null
}


