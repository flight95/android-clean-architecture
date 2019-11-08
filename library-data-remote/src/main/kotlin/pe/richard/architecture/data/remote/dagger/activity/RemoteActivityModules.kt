package pe.richard.architecture.data.remote.dagger.activity

import dagger.Module

@Module(
    includes = [
        RemoteActivityModule.Provider::class,
        RemoteActivityModule.Binder::class
    ]
)
class RemoteActivityModules