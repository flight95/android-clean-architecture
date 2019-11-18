package pe.richard.architecture.boilerplate.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import pe.richard.architecture.boilerplate.R
import pe.richard.architecture.boilerplate.dagger.android.DaggerFragment
import pe.richard.architecture.boilerplate.dagger.view.ViewComponent
import pe.richard.architecture.boilerplate.presenter.view.home.HomePresenter
import pe.richard.architecture.core.lifecycle.extensions.observe
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var presenter: HomePresenter

    override fun getRootViewId(): Int = R.layout.fragment_home

    override fun observeLiveData(owner: LifecycleOwner) {
        with(presenter) {
            observeError(this@HomeFragment) {
                Log.e("HomeFragment", "error: $it")
            }

            auth.observe(this@HomeFragment) {
                Log.e("HomeFragment", "auth: $it")
            }
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {}

    override fun isInitialized(): Boolean = presenter.auth.value != null

    override fun initLiveData(owner: LifecycleOwner) {
        presenter.observeDynamicAuth()
    }

    //region DaggerFragment

    override fun inject(component: ViewComponent) {
        component.inject(this)
    }

    //endregion

}