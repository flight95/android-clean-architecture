package pe.richard.architecture.data.cache.dagger.view

import dagger.Module

@Module(
    includes = [
        CacheViewModule.Provider::class,
        CacheViewModule.Binder::class
    ]
)
class CacheViewModules