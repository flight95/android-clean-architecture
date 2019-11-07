package pe.richard.architecture.data.dagger.activity

import dagger.Module
import pe.richard.architecture.data.cache.dagger.activity.CacheActivityModules
import pe.richard.architecture.data.remote.dagger.activity.RemoteActivityModules

@Module(
    includes = [
        DataActivityModule.Provider::class,
        DataActivityModule.Binder::class,
        RemoteActivityModules::class,
        CacheActivityModules::class
    ]
)
class DataActivityModules