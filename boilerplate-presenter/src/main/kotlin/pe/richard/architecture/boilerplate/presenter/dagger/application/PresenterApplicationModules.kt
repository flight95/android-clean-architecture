package pe.richard.architecture.boilerplate.presenter.dagger.application

import dagger.Module
import pe.richard.architecture.domain.dagger.application.DomainApplicationModules

@Module(
    includes = [
        PresenterApplicationModule.Provider::class,
        PresenterApplicationModule.Binder::class,
        DomainApplicationModules::class
    ]
)
class PresenterApplicationModules