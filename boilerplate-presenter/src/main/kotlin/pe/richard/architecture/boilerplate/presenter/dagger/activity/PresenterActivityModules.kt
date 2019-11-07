package pe.richard.architecture.boilerplate.presenter.dagger.activity

import dagger.Module
import pe.richard.architecture.domain.dagger.activity.DomainActivityModules

@Module(
    includes = [
        PresenterActivityModule.Provider::class,
        PresenterActivityModule.Binder::class,
        DomainActivityModules::class
    ]
)
class PresenterActivityModules