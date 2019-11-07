package pe.richard.architecture.boilerplate.dagger.activity.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity
import pe.richard.architecture.boilerplate.presenter.dagger.activity.test.IPresenterActivityTest
import javax.inject.Inject

internal class ActivityTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val presenter: IPresenterActivityTest
) :
    IActivityTest {

    override fun test() {
        Log.e("ActivityTest", "ActivityTest: $this")
        Log.e("ActivityTest", "application: $application")
        Log.e("ActivityTest", "activity: $activity")
        presenter.test()
    }

}