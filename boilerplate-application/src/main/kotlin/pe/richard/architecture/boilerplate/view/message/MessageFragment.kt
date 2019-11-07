package pe.richard.architecture.boilerplate.view.message

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.richard.architecture.boilerplate.R
import pe.richard.architecture.boilerplate.dagger.activity.test.IActivityTest
import pe.richard.architecture.boilerplate.dagger.android.DaggerFragment
import pe.richard.architecture.boilerplate.dagger.application.test.IApplicationTest
import pe.richard.architecture.boilerplate.dagger.view.ViewComponent
import pe.richard.architecture.boilerplate.dagger.view.test.IViewTest
import javax.inject.Inject

class MessageFragment : DaggerFragment() {

    @Inject
    lateinit var applicationTest: IApplicationTest

    @Inject
    lateinit var activityTest: IActivityTest

    @Inject
    lateinit var viewTest: IViewTest

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_message, container, false)
            ?.also { view -> initRootViewPadding(view) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applicationTest.test()
        activityTest.test()
        viewTest.test()
    }

    //region DaggerFragment

    override fun inject(component: ViewComponent) {
        Log.e("MessageFragment", "==================================================")
        Log.e("MessageFragment", "application: ${activity?.application}")
        Log.e("MessageFragment", "activity: $activity")
        Log.e("MessageFragment", "fragment: $this")
        component.inject(this)
    }

    //endregion

}