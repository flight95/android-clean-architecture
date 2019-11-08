package pe.richard.architecture.boilerplate.presenter.dagger.view

import dagger.Module
import pe.richard.architecture.domain.dagger.view.DomainViewModules

@Module(
    includes = [
        PresenterViewModule.Provider::class,
        PresenterViewModule.Binder::class,
        DomainViewModules::class
    ]
)
class PresenterViewModules