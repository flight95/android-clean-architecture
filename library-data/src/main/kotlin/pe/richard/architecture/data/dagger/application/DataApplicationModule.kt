package pe.richard.architecture.data.dagger.application

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ApplicationScope
import pe.richard.architecture.data.dagger.application.test.DataApplicationTest
import pe.richard.architecture.data.dagger.application.test.IDataApplicationTest

internal object DataApplicationModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ApplicationScope
        abstract fun bindDataApplicationTest(inject: DataApplicationTest): IDataApplicationTest

    }

}