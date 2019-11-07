package pe.richard.architecture.data.dagger.view.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pe.richard.architecture.data.cache.dagger.view.test.ICacheViewTest
import pe.richard.architecture.data.remote.dagger.view.test.IRemoteViewTest
import javax.inject.Inject

internal class DataViewTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val fragment: Fragment,
    private val remoteTest: IRemoteViewTest,
    private val cacheTest: ICacheViewTest
) : IDataViewTest {

    override fun test() {
        Log.e("DataViewTest", "DataViewTest: $this")
        Log.e("DataViewTest", "application: $application")
        Log.e("DataViewTest", "activity: $activity")
        Log.e("DataViewTest", "fragment: $fragment")
        remoteTest.test()
        cacheTest.test()
    }

}