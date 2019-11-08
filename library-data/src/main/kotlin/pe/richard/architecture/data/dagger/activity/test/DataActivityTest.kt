package pe.richard.architecture.data.dagger.activity.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity
import pe.richard.architecture.data.cache.dagger.activity.test.ICacheActivityTest
import pe.richard.architecture.data.remote.dagger.activity.test.IRemoteActivityTest
import javax.inject.Inject

internal class DataActivityTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val remote: IRemoteActivityTest,
    private val cache: ICacheActivityTest
) : IDataActivityTest {

    override fun test() {
        Log.e("DataActivityTest", "DataActivityTest: $this")
        Log.e("DataActivityTest", "application: $application")
        Log.e("DataActivityTest", "activity: $activity")
        remote.test()
        cache.test()
    }

}