package pe.richard.architecture.data.cache.dagger.view.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

internal class CacheViewTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val fragment: Fragment
) : ICacheViewTest {

    override fun test() {
        Log.e("CacheViewTest", "CacheViewTest: $this")
        Log.e("CacheViewTest", "application: $application")
        Log.e("CacheViewTest", "activity: $activity")
        Log.e("CacheViewTest", "fragment: $fragment")
    }

}