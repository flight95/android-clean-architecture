package pe.richard.architecture.domain.dagger.application

import dagger.Module
import pe.richard.architecture.data.dagger.application.DataApplicationModules

@Module(
    includes = [
        DomainApplicationModule.Provider::class,
        DomainApplicationModule.Binder::class,
        DataApplicationModules::class
    ]
)
class DomainApplicationModules