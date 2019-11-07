package pe.richard.architecture.boilerplate.presenter.dagger.activity.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity
import pe.richard.architecture.domain.dagger.activity.test.IDomainActivityTest
import javax.inject.Inject

internal class PresenterActivityTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val domain: IDomainActivityTest
) : IPresenterActivityTest {

    override fun test() {
        Log.e("PresenterActivityTest", "PresenterActivityTest: $this")
        Log.e("PresenterActivityTest", "application: $application")
        Log.e("PresenterActivityTest", "activity: $activity")
        domain.test()
    }

}