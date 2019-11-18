package pe.richard.architecture.core.view.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.findNavController
import pe.richard.architecture.core.view.navigation.NavFragment

abstract class LifecycleFragment : NavFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getRootViewId(), container, false)
            ?.also { view -> initRootViewPadding(view) }

    @LayoutRes
    abstract fun getRootViewId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData(this)
        initView(view, savedInstanceState)
        if (!isInitialized()) initLiveData(this)
    }

    abstract fun observeLiveData(owner: LifecycleOwner)

    abstract fun initView(view: View, savedInstanceState: Bundle?)

    /**
     * If LiveData already has data set, it will be delivered to the observer.
     * And then, do not call initLiveData(owner: LifecycleOwner).
     *
     * @see initLiveData(owner: LifecycleOwner)
     * @return The LiveData was null for check to call initLiveData(owner: LifecycleOwner).
     */
    abstract fun isInitialized(): Boolean

    /**
     * Initialization process requires you to initialized data is null.
     *
     * @see isInitialized()
     * @param owner LifecycleOwner for LiveData.
     */
    abstract fun initLiveData(owner: LifecycleOwner)

    /**
     * Using the NavController's ViewModelStore makes the ViewModel alive, whether or not the Fragment is finished.
     *
     * @see <a href="https://developer.android.com/guide/navigation/navigation-programmatic#share_ui-related_data_between_destinations_with_viewmodel">navigation-programmatic</a>
     * @return ViewModelStore of NavController.
     */
    override fun getViewModelStore(): ViewModelStore =
        findNavController().getViewModelStoreOwner(getNavGraphId()).viewModelStore

    @IdRes
    abstract fun getNavGraphId(): Int

}