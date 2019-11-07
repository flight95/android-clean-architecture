package pe.richard.architecture.data.cache.dagger.application

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ApplicationScope
import pe.richard.architecture.data.cache.dagger.application.test.CacheApplicationTest
import pe.richard.architecture.data.cache.dagger.application.test.ICacheApplicationTest

internal object CacheApplicationModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ApplicationScope
        abstract fun bindCacheApplicationTest(inject: CacheApplicationTest): ICacheApplicationTest

    }

}