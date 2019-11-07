package pe.richard.architecture.domain.dagger.application.test

import android.app.Application
import android.util.Log
import pe.richard.architecture.data.dagger.application.test.IDataApplicationTest
import javax.inject.Inject

internal class DomainApplicationTest @Inject constructor(
    private val application: Application,
    private val data: IDataApplicationTest
) : IDomainApplicationTest {

    override fun test() {
        Log.e("DomainApplicationTest", "DomainApplicationTest: $this")
        Log.e("DomainApplicationTest", "application: $application")
        data.test()
    }

}