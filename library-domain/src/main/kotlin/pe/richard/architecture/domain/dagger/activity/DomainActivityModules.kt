package pe.richard.architecture.domain.dagger.activity

import dagger.Module
import pe.richard.architecture.data.dagger.activity.DataActivityModules

@Module(
    includes = [
        DomainActivityModule.Provider::class,
        DomainActivityModule.Binder::class,
        DataActivityModules::class
    ]
)
class DomainActivityModules