package pe.richard.architecture.data.remote.dagger.application

import dagger.Module

@Module(
    includes = [
        RemoteApplicationModule.Provider::class,
        RemoteApplicationModule.Binder::class
    ]
)
class RemoteApplicationModules