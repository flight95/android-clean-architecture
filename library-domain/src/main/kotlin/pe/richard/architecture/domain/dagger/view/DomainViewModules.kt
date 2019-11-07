package pe.richard.architecture.domain.dagger.view

import dagger.Module
import pe.richard.architecture.data.dagger.view.DataViewModules

@Module(
    includes = [
        DomainViewModule.Provider::class,
        DomainViewModule.Binder::class,
        DataViewModules::class
    ]
)
class DomainViewModules