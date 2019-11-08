package pe.richard.architecture.boilerplate.dagger.application.test

import android.app.Application
import android.util.Log
import pe.richard.architecture.boilerplate.presenter.dagger.application.test.IPresenterApplicationTest
import javax.inject.Inject

internal class ApplicationTest @Inject constructor(
    private val application: Application,
    private val presenter: IPresenterApplicationTest
) : IApplicationTest {

    override fun test() {
        Log.e("ApplicationTest", "ApplicationTest: $this")
        Log.e("ApplicationTest", "application: $application")
        presenter.test()
    }

}