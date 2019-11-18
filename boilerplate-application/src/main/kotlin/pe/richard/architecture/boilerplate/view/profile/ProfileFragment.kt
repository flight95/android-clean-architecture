package pe.richard.architecture.boilerplate.view.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import pe.richard.architecture.boilerplate.R
import pe.richard.architecture.boilerplate.dagger.activity.test.IActivityTest
import pe.richard.architecture.boilerplate.dagger.android.DaggerFragment
import pe.richard.architecture.boilerplate.dagger.application.test.IApplicationTest
import pe.richard.architecture.boilerplate.dagger.view.ViewComponent
import pe.richard.architecture.boilerplate.dagger.view.test.IViewTest
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var applicationTest: IApplicationTest

    @Inject
    lateinit var activityTest: IActivityTest

    @Inject
    lateinit var viewTest: IViewTest

    override fun getRootViewId(): Int = R.layout.fragment_profile

    override fun observeLiveData(owner: LifecycleOwner) {}

    override fun initView(view: View, savedInstanceState: Bundle?) {
        applicationTest.test()
        activityTest.test()
        viewTest.test()
    }

    override fun isInitialized(): Boolean = false

    override fun initLiveData(owner: LifecycleOwner) {}

    //region DaggerFragment

    override fun inject(component: ViewComponent) {
        Log.e("ProfileFragment", "==================================================")
        Log.e("ProfileFragment", "application: ${activity?.application}")
        Log.e("ProfileFragment", "activity: $activity")
        Log.e("ProfileFragment", "fragment: $this")
        component.inject(this)
    }

    //endregion

}