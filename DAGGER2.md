# Dagger2
>Dagger is a fully static, compile-time dependency injection framework for both Java and Android.
It is an adaptation of an earlier version created by Square and now maintained by Google.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)

## Why DI?
> In software engineering, dependency injection is a technique whereby one object supplies the dependencies of another object.
A "dependency" is an object that can be used, for example as a service.
Instead of a client specifying which service it will use, something tells the client what service to use.
The "injection" refers to the passing of a dependency (a service) into the object (a client) that would use it.
The service is made part of the client's state.
Passing the service to the client, rather than allowing a client to build or find the service,
is the fundamental requirement of the pattern.

```kotlin
// Common instantiation code.
val service = Service()
...
val service = Service()
...

// Using DI.
@Inject lateinit var service: Service
...
@Inject lateinit var service: Service
...
```
Change Service instance to Singleton. DI is best way.

## Koin vs Dagger2
> [Koin](https://insert-koin.io) is a pragmatic lightweight dependency injection framework for Kotlin developers.
Written in pure Kotlin, using functional resolution only: no proxy, no code generation, no reflection.
Koin is a DSL, a light container and a pragmatic API.

Koin is very simple than Dagger2, and support easy way. But I chose dagger2 for only two reason.

### Performance
Dagger have best performance.

![Koin](https://miro.medium.com/max/1234/1*6xr83KmNyeitWC9vkSbMsA.png)

### Compile time vs Runtime
Dagger is DI in compile time from annotations. But Koin is not, and occur crash in runtime.

## Dagger2 in Android multi-modules
Dagger have android support library, but have AndroidX issues. I do not used AndroidInjector, and create wrapping classes.
Application have Component, Activity and Fragment have Subcomponent. Modules will be exist in every architecture modules.

### Scopes
Create three scope for android.

```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewScope
```

ViewScope will be follow Fragment lifecycle.

### Provide and Bind modules

```kotlin
interface IViewTest {

    fun test()

}

internal class ViewTest @Inject constructor(
    private val application: Application,
    private val activity: FragmentActivity,
    private val fragment: Fragment,
    private val presenter: IPresenterViewTest
) : IViewTest {

    override fun test() {
        Log.e("ViewTest", "ViewTest: $this")
        Log.e("ViewTest", "application: $application")
        Log.e("ViewTest", "activity: $activity")
        Log.e("ViewTest", "fragment: $fragment")
        presenter.test()
    }

}

internal object ViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        abstract fun bindViewTest(viewTest: ViewTest): IViewTest

    }

}
```

Kotlin was supported internal visibility modifier.
It means that the member is visible within the same module, and used to be Encapsulation.

### Module dependencies
Using includes, define module dependencies.

```kotlin
@Module(
    includes = [
        PresenterViewModule.Provider::class,
        PresenterViewModule.Binder::class,
        DomainViewModules::class
    ]
)
class PresenterViewModules
```

### Using Subcomponents
Activity and Fragment will be used Subcomponent that define module dependencies.

```kotlin
@Subcomponent(
    modules = [
        ViewModule.Provider::class,
        ViewModule.Binder::class,
        PresenterViewModules::class
    ]
)
@ViewScope
interface ViewComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): ViewComponent
    }

    //region TODO: If you want to add another fragment class, you have to inject it here.

    fun inject(fragment: HomeFragment)
    ...

    //endregion

}

@Subcomponent(
    modules = [
        ActivityModule.Subcomponents::class,
        ActivityModule.Provider::class,
        ActivityModule.Binder::class,
        PresenterActivityModules::class
    ]
)
@ActivityScope
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: FragmentActivity): ActivityComponent
    }

    fun viewComponentFactory(): ViewComponent.Factory

    //region TODO: If you want to add another activity class, you have to inject it here.

    fun inject(activity: MainActivity)

    //endregion

}

internal object ActivityModule {

    @Module(subcomponents = [ViewComponent::class])
    class Subcomponents

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ActivityScope
        abstract fun bindActivityTest(inject: ActivityTest): IActivityTest

    }

}
```

### ApplicationComponent
Define main component for application.

```kotlin
@Component(
    modules = [
        ApplicationModule.Subcomponents::class,
        ApplicationModule.Provider::class,
        ApplicationModule.Binder::class,
        PresenterApplicationModules::class
    ]
)
@ApplicationScope
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

    fun activityComponentFactory(): ActivityComponent.Factory

    //region TODO: If you want to add another application class, you have to inject it here.

    fun inject(application: MainApplication)

    //endregion

}
```

### Wrapping classes
Until solved AndroidX issues, I do not used AndroidInjector, and create wrapping classes.

```kotlin
abstract class DaggerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        inject(getComponent())
    }

    lateinit var applicationComponent: ApplicationComponent
        private set

    private fun getComponent(): ApplicationComponent =
        when (this::applicationComponent.isInitialized) {
            true -> applicationComponent
            false -> DaggerApplicationComponent
                .factory()
                .create(this)
                .also { applicationComponent = it }
        }

    abstract fun inject(component: ApplicationComponent)

}

abstract class DaggerActivity : NavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(getComponent())
        super.onCreate(savedInstanceState)
    }

    lateinit var activityComponent: ActivityComponent
        private set

    private fun getComponent(): ActivityComponent =
        when (this::activityComponent.isInitialized) {
            true -> activityComponent
            false -> (application as? DaggerApplication)
                ?.run {
                    applicationComponent
                        .activityComponentFactory()
                        .create(this@DaggerActivity)
                        .also { activityComponent = it }
                }
                ?: throw IllegalArgumentException("No ApplicationComponent was found for $application")
        }

    abstract fun inject(component: ActivityComponent)

}

abstract class DaggerFragment : NavFragment() {

    override fun onAttach(context: Context) {
        inject(getComponent())
        super.onAttach(context)
    }

    private fun getComponent(): ViewComponent =
        (activity as? DaggerActivity)
            ?.run {
                activityComponent
                    .viewComponentFactory()
                    .create(this@DaggerFragment)
            }
            ?: throw IllegalArgumentException("No ActivityComponent was found for $activity")

    abstract fun inject(component: ViewComponent)

}
```

### Inject Application, Activity, Fragments.
Wrapping classes will be support simple way for injecting.

```kotlin
class MainApplication : DaggerApplication() {

    ...

    override fun inject(component: ApplicationComponent) {
        Log.e("MainApplication", "==================================================")
        Log.e("MainApplication", "application: $this")
        component.inject(this)
    }

}

class MainActivity : DaggerActivity() {

    ...

    override fun inject(component: ActivityComponent) {
        Log.e("MainActivity", "==================================================")
        Log.e("MainActivity", "application: $application")
        Log.e("MainActivity", "activity: $this")
        component.inject(this)
    }

}

class HomeFragment : DaggerFragment() {

    ...

    override fun inject(component: ViewComponent) {
        Log.e("HomeFragment", "==================================================")
        Log.e("HomeFragment", "application: ${activity?.application}")
        Log.e("HomeFragment", "activity: $activity")
        Log.e("HomeFragment", "fragment: $this")
        component.inject(this)
    }

}
```

## Understanding DI instances and Scope.
In sources, I call test function chain across each modules.

```
E/HomeFragment
> E/ApplicationTest > E/PresenterApplicationTest > E/DomainApplicationTest > E/DataApplicationTest > ...
> E/ActivityTest > E/PresenterActivityTest > E/DomainActivityTest > E/DataActivityTest > ...

E/MessageFragment
> E/ApplicationTest > E/PresenterApplicationTest > E/DomainApplicationTest > E/DataApplicationTest > ...
> E/ActivityTest > E/PresenterActivityTest > E/DomainActivityTest > E/DataActivityTest > ...
```

Each Test instances is same.
It means that same Application and Activity will be used by @ApplicationScope and @ActivityScope.

```
E/HomeFragment
> E/ViewTest > E/PresenterViewTest > E/DomainViewTest > E/DataViewTest > ...

E/MessageFragment
> E/ViewTest > E/PresenterViewTest > E/DomainViewTest > E/DataViewTest > ...
```

But, ViewTest is different.
It means that different Fragment instance will be used different ViewTest instance by @ViewScope.

```kotlin
internal class ViewTest @Inject constructor(
    private val application: Application, // It it MainApplication in multibindings.
    private val activity: FragmentActivity, // It it MainActivity in multibindings.
    private val fragment: Fragment, // It it each named Fragment in multibindings.
    private val presenter: IPresenterViewTest
) : IViewTest {

    override fun test() {
        Log.e("ViewTest", "ViewTest: $this")
        Log.e("ViewTest", "application: $application")
        Log.e("ViewTest", "activity: $activity")
        Log.e("ViewTest", "fragment: $fragment")
        presenter.test()
    }

}
```

You do not need to use complex multibindings.

This is end. Enjoy it.
