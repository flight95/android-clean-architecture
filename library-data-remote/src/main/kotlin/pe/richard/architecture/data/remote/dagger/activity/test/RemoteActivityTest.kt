package pe.richard.architecture.data.remote.dagger.activity.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

internal class RemoteActivityTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity
) : IRemoteActivityTest {

    override fun test() {
        Log.e("RemoteActivityTest", "RemoteActivityTest: $this")
        Log.e("RemoteActivityTest", "application: $application")
        Log.e("RemoteActivityTest", "activity: $activity")
    }

}