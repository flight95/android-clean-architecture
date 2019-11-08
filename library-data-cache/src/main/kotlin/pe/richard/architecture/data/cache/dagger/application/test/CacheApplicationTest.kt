package pe.richard.architecture.data.cache.dagger.application.test

import android.app.Application
import android.util.Log
import javax.inject.Inject

internal class CacheApplicationTest @Inject constructor(
    private val application: Application
) : ICacheApplicationTest {

    override fun test() {
        Log.e("CacheApplicationTest", "CacheApplicationTest: $this")
        Log.e("CacheApplicationTest", "application: $application")
    }

}