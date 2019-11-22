# Android Clean Architecture
>Android Clean Architecture Boilerplate

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Language: Kotlin](https://img.shields.io/badge/Language-Kotlin-547ab7.svg)](https://kotlinlang.org/)
[![Architecture: Clean](https://img.shields.io/badge/Architecture-Clean-green.svg)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

## Clean Architecture
- See [Clean Code Blog](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

![Modules](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

### UI: [name]-application module
> The [name]-application module supports the use of the same Use Cases and Entities when creating multiple applications.

- Manage views and [Android DataBinding](https://developer.android.com/topic/libraries/data-binding). UI event handling.
- Using [Android Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle) and another related [Jetpack Components](https://developer.android.com/jetpack).
- Manage [Dagger2 Components](https://dagger.dev/users-guide) and [Dagger2 SubComponents](https://dagger.dev/subcomponents).

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)
[![Android: Navigation](https://img.shields.io/badge/Android-Navigation-yellow.svg)](https://developer.android.com/guide/navigation)
[![Android: Lifecycles](https://img.shields.io/badge/Android-Lifecycles-yellow.svg)](https://developer.android.com/topic/libraries/architecture/lifecycle)
[![Android: DataBinding](https://img.shields.io/badge/Android-DataBinding-yellow.svg)](https://developer.android.com/topic/libraries/data-binding)

### Presenters: [name]-presenter module
> The [name]-presenter module supports the use of the same Use Cases and Entities when creating multiple applications.
> [name]-application and [name]-presenter was matched 1 : 1.

- Manage [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and [Android ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel). Connect View and Domain.
- Using Rx and manage [CompositeDisposable](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/CompositeDisposable.html).
- Define [Dagger2 modules](https://dagger.dev/users-guide) for presenter layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)
[![Android: LiveData](https://img.shields.io/badge/Android-LiveData-yellow.svg)](https://developer.android.com/topic/libraries/architecture/livedata)
[![Android: ViewModel](https://img.shields.io/badge/Android-ViewModel-yellow.svg)](https://developer.android.com/topic/libraries/architecture/viewmodel)

### Use Cases: library-domain module
- Slicing business logic. Connect Presenter and Data. Best Unit Test point.
- UseCase: Slicing is smaller than the MVVM ViewModel.
- MVP was simple, but exist duplicated code. MVVM was the opposite. Using Presenter with UseCase reduces the disadvantages.
- Using Rx and pass [CompositeDisposable](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/CompositeDisposable.html).
- Define [Dagger2 modules](https://dagger.dev/users-guide) for domain layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)

### Entities: library-data module
- Data Entities and Data Repositories: Business Logic by each Entity type. Remote and Cache Entities handling.
- Using Rx and need [CompositeDisposable](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/CompositeDisposable.html).
- Define [Dagger2 modules](https://dagger.dev/users-guide) for data layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)

### Entities for remote: library-data-remote module
- Remote Entities and Data Sources: Remote Business Logic by each Entity type.
- Define [Dagger2 modules](https://dagger.dev/users-guide) for data-remote layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)
[![Library: Retrofit2](https://img.shields.io/badge/Library-Retrofit2-orange.svg)](https://square.github.io/retrofit/)
[![Library: Okhttp3](https://img.shields.io/badge/Library-Okhttp3-orange.svg)](https://square.github.io/okhttp/)
[![AWS: Cognito](https://img.shields.io/badge/AWS-Cognito-yellowgreen.svg)](https://aws.amazon.com/ko/cognito/?hp=tile&so-exp=below)
[![AWS: ApiGateway](https://img.shields.io/badge/AWS-ApiGateway-yellowgreen.svg)](https://aws.amazon.com/ko/api-gateway/?hp=tile&so-exp=below)
[![AWS: AppSync](https://img.shields.io/badge/AWS-AppSync-yellowgreen.svg)](https://aws.amazon.com/ko/appsync/?hp=tile&so-exp=below)
[![AWS: RDS](https://img.shields.io/badge/AWS-RDS-yellowgreen.svg)](https://aws.amazon.com/ko/rds/?hp=tile&so-exp=below)
[![AWS: DocumentDB](https://img.shields.io/badge/AWS-DocumentDB-yellowgreen.svg)](https://aws.amazon.com/ko/documentdb/?hp=tile&so-exp=below)
[![AWS: S3](https://img.shields.io/badge/AWS-S3-yellowgreen.svg)](https://aws.amazon.com/ko/s3/?hp=tile&so-exp=below)
[![Google: Auth](https://img.shields.io/badge/Google-Auth-yellow.svg)](https://developers.google.com/identity/protocols/OAuth2)
[![Firebase: Auth](https://img.shields.io/badge/Firebase-Auth-yellow.svg)](https://firebase.google.com/products/auth/)
[![Firebase: Functions](https://img.shields.io/badge/Firebase-Functions-yellow.svg)](https://firebase.google.com/products/functions/)
[![Firebase: Firestore](https://img.shields.io/badge/Firebase-Firestore-yellow.svg)](https://firebase.google.com/products/firestore/)
[![Firebase: Storage](https://img.shields.io/badge/Firebase-Storage-yellow.svg)](https://firebase.google.com/products/storage/)

### Entities for cache: library-data-cache module
- Cache Entities and Data Sources: Cache Business Logic by each Entity type.
- Define [Dagger2 modules](https://dagger.dev/users-guide) for data-cache layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)
[![Android: Room](https://img.shields.io/badge/Android-Room-yellow.svg)](https://developer.android.com/topic/libraries/architecture/room)

### Entity Utilities: library-data-core module
- Data interface and utility library for all data modules.

### Utilities: library-core module
- Common utility library for all modules.

## Logic and Functions naming

### Create, update and delete.
> When you are offline, you can also allow data changes so that you can sync when it changes to online. However, because it is too complicated, it is recommended to change the data only while online.

Create or Insert
- [Completable](https://reactivex.io/RxJava/javadoc/io/reactivex/Completable.html) is default.
- [Single](https://reactivex.io/RxJava/javadoc/io/reactivex/Single.html) with auto generated object by id will be useful.

```kotlin
fun createData(data: Data): Completable {...}
fun createData(data: Data): Single<Data> {...}

fun createData(id: String): Completable {...}
fun createData(id: String): Single<Data> {...}
```

Update
- [Completable](https://reactivex.io/RxJava/javadoc/io/reactivex/Completable.html) is default.
- [Single](https://reactivex.io/RxJava/javadoc/io/reactivex/Single.html) with passed object from parameter will be useful.

```kotlin
fun updateData(data: Data): Completable {...}
fun updateData(data: Data): Single<Data> {...}
```

Set
> It is mainly used to synchronize remote data locally.
- [Completable](https://reactivex.io/RxJava/javadoc/io/reactivex/Completable.html) is default.
- [Single](https://reactivex.io/RxJava/javadoc/io/reactivex/Single.html) with passed object from parameter will be useful.
- Set creates it if there is no value, and updates it if it exists.

```kotlin
fun setData(data: Data): Completable {...}
fun setData(data: Data): Single<Data> {...}
```

### Read or monitoring value changed.
> Depending on the nature of data, you can fetch data by combining multiple DataSources. 

Get
- [Maybe](https://reactivex.io/RxJava/javadoc/io/reactivex/Maybe.html) is default: If UI will be updated in only not null status, it will be useful.
- [Single](https://reactivex.io/RxJava/javadoc/io/reactivex/Single.html) will need error indicating null status: If UI will be updated in null or not null status, it will be useful.

```kotlin
fun getData(data: Data): Maybe<Data> {...}
fun getData(data: Data): Single<Data> {...} // with DataEmptyError.
```

Observe: Monitoring value changed
- [Flowable](https://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html) with last [Backpressure](https://github.com/ReactiveX/RxJava/wiki/Backpressure) will be only way.

```kotlin
fun observeData(data: Data): Flowable<Data> {...}
```

### The nature of data.

Remote
> Data that should not be cached.

```kotlin
fun getRemoteData(id: String): Single<Data> =
    remote.getData(id)
    
fun observeRemoteData(id: String): Flowable<Data> =
    remote.observeData(id)
```

Cache
> Synchronization is implemented separately. Data just care about the cache.

```kotlin
fun getCacheData(id: String): Single<Data> =
    cache.getData(id)
    
fun observeCacheData(id: String): Flowable<Data> =
    cache.observeData(id)
```

Sync
> Get uncached data from remote, and cache it.

```kotlin
fun getSyncData(id: String): Single<Data> =
    remote.getData(id)
        .flatMap { data ->
            cache.setData(data) // retun Completable
                .toSingleDefault(data) // return Single from remote.
                .onErrorReturn { data } // ignore error.
        }
    
fun observeSyncData(id: String): Flowable<Data> =
    remote.observeData(id)
        .flatMap { data ->
            cache.setData(data) // retun Completable
                .toSingleDefault(data) // return Single from remote.
                .onErrorReturn { data } // ignore error.
                .toFlowable()
            }
```

Static
> Once created, the data never changes.

```kotlin
fun getStaticData(id: String): Single<Data> =
    cache.getData(id)
        .onErrorResumeNext(getSyncData(id))
```

Dynamic
> Cacheable but frequently changed data.

```kotlin
fun observeDynamicData(id: String): Flowable<Data> =
    getStaticData(id)
        .flatMapPublisher {
            Single.just(it).run {
                when(it.from) {
                    is From.Cache -> concatWith(getSyncData(id))
                    is From.Remote -> toFlowable()
                }
            }
        }
```

## Android Navigation Architecture and Material Theme
See [Description](https://github.com/flight95/android-clean-architecture/blob/develop/NAVIGATION_MATERIAL.md) file.

## Android DI from Dagger2
See [Description](https://github.com/flight95/android-clean-architecture/blob/develop/DAGGER2.md) file.

## Google and Firebase authentication.
See [Description](https://github.com/flight95/android-clean-architecture/blob/develop/AUTH_GOOGLE_FIREBASE.md) file.

## Using Firebase Firestore.
See [Description](https://github.com/flight95/android-clean-architecture/blob/feature-firestore/FIRESTORE.md) file.

## Git Flow
- Use the default Git Flow.
![Branches](http://woowabros.github.io/img/2017-10-30/git-flow_overall_graph.png)
