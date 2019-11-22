package pe.richard.architecture.boilerplate.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import pe.richard.architecture.boilerplate.R
import pe.richard.architecture.boilerplate.dagger.android.DaggerFragment
import pe.richard.architecture.boilerplate.dagger.view.ViewComponent
import pe.richard.architecture.boilerplate.presenter.view.home.HomePresenter
import pe.richard.architecture.core.error.ApplicationError
import pe.richard.architecture.core.lifecycle.extensions.observe
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var presenter: HomePresenter

    override fun getRootViewId(): Int = R.layout.fragment_home

    override fun observeLiveData(owner: LifecycleOwner) {
        with(presenter) {
            observeError(this@HomeFragment) {
                it?.let { error ->
                    when (error) {
                        is ApplicationError -> Log.e("HomeFragment", "error: ${error.getDisplayMessage(context)}", error)
                        else -> Log.e("HomeFragment", "error: ${it.localizedMessage}", error)
                    }
                }
            }

            auth.observe(this@HomeFragment) {
                Log.e("HomeFragment", "auth: $it")
                it?.let {
//                    presenter.createUser(it.email)
//                    presenter.getUserByEmail(it.email)
//                    presenter.createMy()
                    presenter.getMy()
                }
            }

            user.observe(this@HomeFragment) {
                Log.e("HomeFragment", "user: $it")
                it?.let {
//                    presenter.observeUser(it.id)
//                    presenter.getUser(it.id)
//                    presenter.deleteUser(it.id)
//                    presenter.updateUser(
//                        User(
//                            it.from,
//                            it.id,
//                            it.createdAt,
//                            it.updatedAt,
//                            it.deletedAt,
//                            "nyjsarang@icloud.com"
//                        )
//                    )
                }
            }

            my.observe(this@HomeFragment) {
                Log.e("HomeFragment", "my: $it")
                it?.let {
//                    presenter.updateMy(it)
                    presenter.deleteMy()
                }
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