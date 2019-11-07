package pe.richard.architecture.data.dagger.view

import dagger.Module
import pe.richard.architecture.data.cache.dagger.view.CacheViewModules
import pe.richard.architecture.data.remote.dagger.view.RemoteViewModules

@Module(
    includes = [
        DataViewModule.Provider::class,
        DataViewModule.Binder::class,
        RemoteViewModules::class,
        CacheViewModules::class
    ]
)
class DataViewModules