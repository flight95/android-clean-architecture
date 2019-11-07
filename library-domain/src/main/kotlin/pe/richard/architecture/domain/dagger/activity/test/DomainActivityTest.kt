package pe.richard.architecture.domain.dagger.activity.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity
import pe.richard.architecture.data.dagger.activity.test.IDataActivityTest
import javax.inject.Inject

internal class DomainActivityTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val data: IDataActivityTest
) : IDomainActivityTest {

    override fun test() {
        Log.e("DomainActivityTest", "DomainActivityTest: $this")
        Log.e("DomainActivityTest", "application: $application")
        Log.e("DomainActivityTest", "activity: $activity")
        data.test()
    }

}