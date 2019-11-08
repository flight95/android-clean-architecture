package pe.richard.architecture.data.remote.dagger.application.test

import android.app.Application
import android.util.Log
import javax.inject.Inject

internal class RemoteApplicationTest @Inject constructor(
    private val application: Application
) : IRemoteApplicationTest {

    override fun test() {
        Log.e("RemoteApplicationTest", "RemoteApplicationTest: $this")
        Log.e("RemoteApplicationTest", "application: $application")
    }

}