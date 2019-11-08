package pe.richard.architecture.domain.dagger.view.test

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pe.richard.architecture.data.dagger.view.test.IDataViewTest
import javax.inject.Inject

internal class DomainViewTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val fragment: Fragment,
    private val data: IDataViewTest
) : IDomainViewTest {

    override fun test() {
        Log.e("DomainViewTest", "DomainViewTest: $this")
        Log.e("DomainViewTest", "application: $application")
        Log.e("DomainViewTest", "activity: $activity")
        Log.e("DomainViewTest", "fragment: $fragment")
        data.test()
    }

}