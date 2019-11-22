# Firebase Firestore
[![Firebase: Firestore](https://img.shields.io/badge/Firebase-Firestore-yellow.svg)](https://firebase.google.com/products/firestore/)
> Cloud Firestore is a NoSQL document database that lets you easily store, sync, and query data for your mobile and web apps - at global scale.

## Setting with Dagger2
[![Library: Dagger2](https://img.shields.io/badge/Library-Dagger2-orange.svg)](https://dagger.dev)

In [Firebase Console](https://console.firebase.google.com/), enable database. And see [document](https://firebase.google.com/docs/firestore/quickstart?authuser=0).

FirebaseFirestore will be set in application scope, and inject using constructor.
```kotlin
internal object RemoteApplicationModule {

    @Module
    class Provider {

        @Provides
        @ApplicationScope
        fun provideFirestore(): FirebaseFirestore =
            FirebaseFirestore.getInstance()
                .apply {
                    firestoreSettings =
                        FirebaseFirestoreSettings.Builder()
                            .setPersistenceEnabled(false) // Do not use persistence. Cache will be implemented directly.
                            .build()
                }

    }
...
}

internal abstract class RemoteFirestoreDataSource constructor(
    private val service: FirebaseFirestore
) {...}

internal class RemoteFirestoreUserDataSource @Inject constructor(
    service: FirebaseFirestore
) : RemoteFirestoreDataSource(service), UserDataSource {...}
```

## RemoteFirestoreDataSource
> This class supports basic CRUD using Firestore.

It is recommended that you maintain a 1:1 relationship between collections and data sources.
```kotlin
protected abstract val collection: String

service.collection(collection)
```

### Observe and get with Rx
Observe will be removed using ListenerRegistration and Rx can be set cancellable.

See [Get realtime updates with Cloud Firestore](https://firebase.google.com/docs/firestore/query-data/listen?authuser=0).
```kotlin
private val observers = mutableMapOf<FlowableEmitter<Map<String, Any?>>, ListenerRegistration>()

emitter.setCancellable { observers[emitter]?.remove() }
```

You can get data in two ways: document id and query.

See [Get a document](https://firebase.google.com/docs/firestore/query-data/get-data?authuser=0#get_a_document)
and [Get multiple documents from a collection](https://firebase.google.com/docs/firestore/query-data/get-data?authuser=0#get_multiple_documents_from_a_collection).

```kotlin
service.collection(collection)
    .document(id)
    .get(Source.SERVER)

service.collection(collection)
    .whereEqualTo(field, value)
    .get(Source.SERVER)
```

### Create using auto-generate an ID
Sometimes there isn't a meaningful ID for the document,
and it's more convenient to let Cloud Firestore auto-generate an ID for you.

See [Add a document](https://firebase.google.com/docs/firestore/manage-data/add-data?authuser=0#add_a_document).
```kotlin
service.collection(collection)
   .add(data)
```

### Update and delete
To update some fields of a document without overwriting the entire document.

And instead of actually deleting the data, you might want to update the delete date field to change it to the deleted state.

See [Update a document](https://firebase.google.com/docs/firestore/manage-data/add-data?authuser=0#update-data).
```kotlin
service.collection(collection)
    .document(id)
    .update(data)
```

To actually delete the data, use delete function.

See [Delete data from Cloud Firestore](https://firebase.google.com/docs/firestore/manage-data/delete-data?authuser=0).
```kotlin
service.collection(collection)
    .document(id)
    .delete()
```

## Secure and validate data
[![Firebase: Functions](https://img.shields.io/badge/Firebase-Functions-yellow.svg)](https://firebase.google.com/products/functions/)

Firestore can be set Security Rules, but not fully supported yet.
If use create, update and delete, using [Cloud Functions for Firebase](https://firebase.google.com/docs/functions?authuser=0).

See [Get started with Cloud Firestore Security Rules](https://firebase.google.com/docs/firestore/security/get-started?authuser=0).
