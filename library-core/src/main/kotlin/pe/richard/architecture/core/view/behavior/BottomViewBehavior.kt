package pe.richard.architecture.core.view.behavior

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.animation.AnimationUtils

class BottomViewBehavior<V : View>(context: Context, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<V>(context, attrs) {

    companion object {
        private const val ENTER_ANIMATION_DURATION = 225
        private const val EXIT_ANIMATION_DURATION = 175
        private const val STATE_SCROLLED_DOWN = 1
        private const val STATE_SCROLLED_UP = 2
    }

    var enabled: Boolean = true

    private var height = 0
    private var currentState =
        STATE_SCROLLED_UP
    private var currentAnimator: ViewPropertyAnimator? = null

    override fun onLayoutChild(parent: CoordinatorLayout, child: V, layoutDirection: Int): Boolean {
        (child.layoutParams as? ViewGroup.MarginLayoutParams)
            ?.let { params ->
                height = child.measuredHeight + params.bottomMargin
            }
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int
    ): Boolean =
        enabled && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        when {
            dyConsumed > 0 || dyUnconsumed > 0 -> slideDown(child)
            dyConsumed < 0 || dyUnconsumed < 0 -> slideUp(child)
            else -> {
                // Do nothing.
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun slideUp(child: V) {
        when (currentState) {
            STATE_SCROLLED_UP -> {
                // Do nothing.
            }
            else -> {
                if (currentAnimator != null) {
                    currentAnimator!!.cancel()
                    child.clearAnimation()
                }
                currentState =
                    STATE_SCROLLED_UP
                animateChildTo(
                    child,
                    0,
                    ENTER_ANIMATION_DURATION.toLong(),
                    AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR
                )
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun slideDown(child: V) {
        when (currentState) {
            STATE_SCROLLED_DOWN -> {
                // Do nothing.
            }
            else -> {
                if (currentAnimator != null) {
                    currentAnimator!!.cancel()
                    child.clearAnimation()
                }
                currentState =
                    STATE_SCROLLED_DOWN
                animateChildTo(
                    child,
                    height,
                    EXIT_ANIMATION_DURATION.toLong(),
                    AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
                )
            }
        }
    }

    private fun animateChildTo(
        child: V,
        targetY: Int,
        duration: Long,
        interpolator: TimeInterpolator
    ) {
        currentAnimator = child
            .animate()
            .translationY(targetY.toFloat())
            .setInterpolator(interpolator)
            .setDuration(duration)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        currentAnimator = null
                    }
                })
    }

}