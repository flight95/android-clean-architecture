package pe.richard.architecture.data.cache.dagger.activity

import dagger.Module

@Module(
    includes = [
        CacheActivityModule.Provider::class,
        CacheActivityModule.Binder::class
    ]
)
class CacheActivityModules