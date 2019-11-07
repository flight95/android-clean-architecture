package pe.richard.architecture.data.cache.dagger.activity.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

internal class CacheActivityTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity
) : ICacheActivityTest {

    override fun test() {
        Log.e("CacheActivityTest", "CacheActivityTest: $this")
        Log.e("CacheActivityTest", "application: $application")
        Log.e("CacheActivityTest", "activity: $activity")
    }

}