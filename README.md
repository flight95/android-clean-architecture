# Android Clean Architecture
>Android Clean Architecture Boilerplate

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Language: Kotlin](https://img.shields.io/badge/Language-Kotlin-547ab7.svg)](https://kotlinlang.org/)
[![Architecture: Clean](https://img.shields.io/badge/Architecture-Clean-green.svg)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

## Clean Architecture
- See [Clean Coder Blog](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

![Modules](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

### UI
#### [name]-application module
> The [name]-application module supports the use of the same Use Cases and Entities when creating multiple applications.

- Manage views and [Android DataBinding](https://developer.android.com/topic/libraries/data-binding). UI event handling.
- Using [Android Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle) and another related [Jetpack Components](https://developer.android.com/jetpack).
- Manage [Dagger2 Components](https://dagger.dev/users-guide) and [Dagger2 SubComponents](https://dagger.dev/subcomponents).

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)
[![Android: Navigation](https://img.shields.io/badge/Android-Navigation-yellow.svg)](https://developer.android.com/guide/navigation)
[![Android: Lifecycles](https://img.shields.io/badge/Android-Lifecycles-yellow.svg)](https://developer.android.com/topic/libraries/architecture/lifecycle)
[![Android: DataBinding](https://img.shields.io/badge/Android-DataBinding-yellow.svg)](https://developer.android.com/topic/libraries/data-binding)

### Presenters
#### [name]-presenter module
> The [name]-presenter module supports the use of the same Use Cases and Entities when creating multiple applications.
> [name]-application and [name]-presenter was matched 1 : 1.

- Manage [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and [Android ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel). Connect View and Domain.
- Using Rx and manage [CompositeDisposable](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/CompositeDisposable.html).
- Define [Dagger2 modules](https://dagger.dev/users-guide) for presenter layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)
[![Android: LiveData](https://img.shields.io/badge/Android-LiveData-yellow.svg)](https://developer.android.com/topic/libraries/architecture/livedata)
[![Android: ViewModel](https://img.shields.io/badge/Android-ViewModel-yellow.svg)](https://developer.android.com/topic/libraries/architecture/viewmodel)

### Use Cases
#### library-domain module
- Slicing business logic. Connect Presenter and Data. Best Unit Test point.
- UseCase: Slicing is smaller than the MVVM ViewModel.
- MVP was simple, but exist duplicated code. MVVM was the opposite. Using Presenter with UseCase reduces the disadvantages.
- Using Rx and pass [CompositeDisposable](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/CompositeDisposable.html).
- Define [Dagger2 modules](https://dagger.dev/users-guide) for domain layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)

### Entities
#### library-data module
- Data Entities and Data Repositories: Business Logic by each Entity type. Remote and Cache Entities handling.
- Using Rx and need [CompositeDisposable](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/CompositeDisposable.html).
- Define [Dagger2 modules](https://dagger.dev/users-guide) for data layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)

#### library-data-remote module
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

#### library-data-cache module
- Cache Entities and Data Sources: Cache Business Logic by each Entity type.
- Define [Dagger2 modules](https://dagger.dev/users-guide) for data-cache layer.

[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)
[![Library: Rx2](https://img.shields.io/badge/Library-Rx2-orange.svg)](https://reactivex.io)
[![Android: Room](https://img.shields.io/badge/Android-Room-yellow.svg)](https://developer.android.com/topic/libraries/architecture/room)

#### library-data-core module
- Data interface and utility library for all data modules.

### Utilities
#### library-core module
- Common utility library for all modules.

## Git Flow
- Use the default Git Flow.
![Branches](http://woowabros.github.io/img/2017-10-30/git-flow_overall_graph.png)
