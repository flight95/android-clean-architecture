package pe.richard.architecture.data.cache.dagger.activity

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ActivityScope
import pe.richard.architecture.data.cache.dagger.activity.test.CacheActivityTest
import pe.richard.architecture.data.cache.dagger.activity.test.ICacheActivityTest

internal object CacheActivityModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ActivityScope
        abstract fun bindCacheActivityTest(inject: CacheActivityTest): ICacheActivityTest

    }

}