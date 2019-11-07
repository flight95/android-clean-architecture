package pe.richard.architecture.data.cache.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.data.cache.dagger.view.test.CacheViewTest
import pe.richard.architecture.data.cache.dagger.view.test.ICacheViewTest

internal object CacheViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindCacheViewTest(inject: CacheViewTest): ICacheViewTest

    }

}