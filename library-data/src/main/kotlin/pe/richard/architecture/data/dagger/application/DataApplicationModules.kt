package pe.richard.architecture.data.dagger.application

import dagger.Module
import pe.richard.architecture.data.cache.dagger.application.CacheApplicationModules
import pe.richard.architecture.data.remote.dagger.application.RemoteApplicationModules

@Module(
    includes = [
        DataApplicationModule.Provider::class,
        DataApplicationModule.Binder::class,
        RemoteApplicationModules::class,
        CacheApplicationModules::class
    ]
)
class DataApplicationModules