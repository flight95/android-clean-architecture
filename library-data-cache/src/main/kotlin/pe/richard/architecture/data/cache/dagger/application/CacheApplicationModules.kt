package pe.richard.architecture.data.cache.dagger.application

import dagger.Module

@Module(
    includes = [
        CacheApplicationModule.Provider::class,
        CacheApplicationModule.Binder::class
    ]
)
class CacheApplicationModules