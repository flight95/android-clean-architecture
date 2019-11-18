package pe.richard.architecture.boilerplate.dagger.activity

import androidx.fragment.app.FragmentActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import pe.richard.architecture.boilerplate.R
import pe.richard.architecture.boilerplate.dagger.activity.test.ActivityTest
import pe.richard.architecture.boilerplate.dagger.activity.test.IActivityTest
import pe.richard.architecture.boilerplate.dagger.view.ViewComponent
import pe.richard.architecture.core.dagger.scope.ActivityScope
import javax.inject.Named

internal object ActivityModule {

    @Module(subcomponents = [ViewComponent::class])
    class Subcomponents

    @Module
    class Provider {

        @Provides
        @ActivityScope
        @Named("firebaseClientId") // It is web client id in google cloud platform that will be used to authentication in google and firebase.
        fun provideFirebaseClientId(activity: FragmentActivity): String = activity.getString(R.string.default_web_client_id)

    }

    @Module
    abstract class Binder {

        @Binds
        @ActivityScope
        abstract fun bindActivityTest(inject: ActivityTest): IActivityTest

    }

}