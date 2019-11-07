package pe.richard.architecture.core.view.navigation

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import pe.richard.architecture.core.R
import pe.richard.architecture.core.view.navigation.NavUI.Companion.create

/**
 * UI data class for NavFragment.
 * Call create(context: Context, arguments: Bundle?): NavUI, set UI properties from navigation arguments.
 * It will be supported UI control from nav_graph.xml.
 *
 * @see NavFragment
 * @see create(context: Context, arguments: Bundle?): NavUI
 */
data class NavUI(
    val enableAppBar: Boolean,
    val enableBottomNavigation: Boolean,
    val enableBottomNavigationBehavior: Boolean,
    val enableFloatingActionButton: Boolean
) {
    companion object {

        fun create(context: Context, arguments: Bundle?): NavUI =
            arguments?.run {
                when (getBoolean(context, R.string.argument_popup, false)) {
                    true -> createPopup()
                    false -> NavUI(
                        getBoolean(context, R.string.argument_app_bar_enable, false),
                        getBoolean(context, R.string.argument_bottom_navigation_enable, true),
                        getBoolean(context, R.string.argument_bottom_navigation_behavior_enable, true),
                        getBoolean(context, R.string.argument_floating_action_button_enable, false)
                    )
                }
            } ?: create()

        private fun create() =
            NavUI(
                enableAppBar = false,
                enableBottomNavigation = true,
                enableBottomNavigationBehavior = true,
                enableFloatingActionButton = false
            )

        private fun createPopup() =
            NavUI(
                enableAppBar = false,
                enableBottomNavigation = false,
                enableBottomNavigationBehavior = false,
                enableFloatingActionButton = false
            )

        private fun Bundle.getBoolean(
            context: Context,
            @StringRes key: Int,
            defaultValue: Boolean
        ) = getBoolean(context.getString(key), defaultValue)

    }

}