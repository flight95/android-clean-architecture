package pe.richard.architecture.data.dagger.application.test

import android.app.Application
import android.util.Log
import pe.richard.architecture.data.cache.dagger.application.test.ICacheApplicationTest
import pe.richard.architecture.data.remote.dagger.application.test.IRemoteApplicationTest
import javax.inject.Inject

internal class DataApplicationTest @Inject constructor(
    private val application: Application,
    private val remote: IRemoteApplicationTest,
    private val cache: ICacheApplicationTest
) : IDataApplicationTest {

    override fun test() {
        Log.e("DataApplicationTest", "DataApplicationTest: $this")
        Log.e("DataApplicationTest", "application: $application")
        remote.test()
        cache.test()
    }

}