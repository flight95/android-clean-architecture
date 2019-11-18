package pe.richard.architecture.boilerplate.dagger.application

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import pe.richard.architecture.boilerplate.R
import pe.richard.architecture.boilerplate.dagger.activity.ActivityComponent
import pe.richard.architecture.boilerplate.dagger.application.test.ApplicationTest
import pe.richard.architecture.boilerplate.dagger.application.test.IApplicationTest
import pe.richard.architecture.core.dagger.scope.ActivityScope
import pe.richard.architecture.core.dagger.scope.ApplicationScope
import javax.inject.Named

internal object ApplicationModule {

    @Module(subcomponents = [ActivityComponent::class])
    class Subcomponents

    @Module
    class Provider {

        @Provides
        @ApplicationScope
        @Named("firebaseClientId") // It is web client id in google cloud platform that will be used to authentication in google and firebase.
        fun provideFirebaseClientId(application: Application): String = application.getString(R.string.default_web_client_id)

    }

    @Module
    abstract class Binder {

        @Binds
        @ApplicationScope
        abstract fun bindApplicationTest(inject: ApplicationTest): IApplicationTest

    }

}