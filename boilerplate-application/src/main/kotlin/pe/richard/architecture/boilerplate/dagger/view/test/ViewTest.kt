package pe.richard.architecture.boilerplate.dagger.view.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pe.richard.architecture.boilerplate.presenter.dagger.view.test.IPresenterViewTest
import javax.inject.Inject

internal class ViewTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val fragment: Fragment,
    private val presenter: IPresenterViewTest
) : IViewTest {

    override fun test() {
        Log.e("ViewTest", "ViewTest: $this")
        Log.e("ViewTest", "application: $application")
        Log.e("ViewTest", "activity: $activity")
        Log.e("ViewTest", "fragment: $fragment")
        presenter.test()
    }

}