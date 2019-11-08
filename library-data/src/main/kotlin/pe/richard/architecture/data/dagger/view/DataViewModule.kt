package pe.richard.architecture.data.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.data.dagger.view.test.DataViewTest
import pe.richard.architecture.data.dagger.view.test.IDataViewTest

internal object DataViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindDataViewTest(inject: DataViewTest): IDataViewTest

    }

}