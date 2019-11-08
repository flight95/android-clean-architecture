package pe.richard.architecture.boilerplate.presenter.dagger.view.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pe.richard.architecture.domain.dagger.view.test.IDomainViewTest
import javax.inject.Inject

internal class PresenterViewTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val fragment: Fragment,
    private val domain: IDomainViewTest
) : IPresenterViewTest {

    override fun test() {
        Log.e("PresenterViewTest", "PresenterViewTest: $this")
        Log.e("PresenterViewTest", "application: $application")
        Log.e("PresenterViewTest", "activity: $activity")
        Log.e("PresenterViewTest", "fragment: $fragment")
        domain.test()
    }

}