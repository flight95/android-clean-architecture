package pe.richard.architecture.boilerplate.presenter.dagger.application.test

import android.app.Application
import android.util.Log
import pe.richard.architecture.domain.dagger.application.test.IDomainApplicationTest
import javax.inject.Inject

internal class PresenterApplicationTest @Inject constructor(
    private val application: Application,
    private val domain: IDomainApplicationTest
) : IPresenterApplicationTest {

    override fun test() {
        Log.e("PresenterApplicationTest", "PresenterApplicationTest: $this")
        Log.e("PresenterApplicationTest", "application: $application")
        domain.test()
    }

}