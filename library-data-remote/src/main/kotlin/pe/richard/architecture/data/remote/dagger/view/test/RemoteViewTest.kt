package pe.richard.architecture.data.remote.dagger.view.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

internal class RemoteViewTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val fragment: Fragment
) : IRemoteViewTest {

    override fun test() {
        Log.e("RemoteViewTest", "RemoteViewTest: $this")
        Log.e("RemoteViewTest", "application: $application")
        Log.e("RemoteViewTest", "activity: $activity")
        Log.e("RemoteViewTest", "fragment: $fragment")
    }

}