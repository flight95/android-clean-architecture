package pe.richard.architecture.data.dagger.activity

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ActivityScope
import pe.richard.architecture.data.dagger.activity.test.DataActivityTest
import pe.richard.architecture.data.dagger.activity.test.IDataActivityTest

internal object DataActivityModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ActivityScope
        abstract fun bindDataActivityTest(inject: DataActivityTest): IDataActivityTest

    }

}