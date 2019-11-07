package pe.richard.architecture.data.remote.dagger.view

import dagger.Module

@Module(
    includes = [
        RemoteViewModule.Provider::class,
        RemoteViewModule.Binder::class
    ]
)
class RemoteViewModules