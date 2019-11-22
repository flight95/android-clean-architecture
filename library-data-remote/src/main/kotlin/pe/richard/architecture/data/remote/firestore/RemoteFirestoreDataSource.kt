package pe.richard.architecture.data.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Source
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal abstract class RemoteFirestoreDataSource constructor(
    private val service: FirebaseFirestore
) {

    protected abstract val collection: String

    private val observers = mutableMapOf<FlowableEmitter<Map<String, Any?>>, ListenerRegistration>()

    protected fun observeFirestore(
        id: String,
        nullable: Throwable,
        error: (Throwable) -> Throwable,
        catchable: (Throwable) -> Throwable = error
    ): Flowable<Map<String, Any?>> =
        Flowable
            .create<Map<String, Any?>>(
                { emitter ->
                    emitter.setCancellable { observers[emitter]?.remove() }
                    try {
                        observers[emitter] =
                            service.collection(collection)
                                .document(id)
                                .addSnapshotListener { snapshot, e ->
                                    when (e) {
                                        null -> when (val data = snapshot?.data) {
                                            null -> emitter.onError(nullable)
                                            else -> emitter.onNext(data.setIdentifier(id))
                                        }
                                        else -> emitter.onError(error(e))
                                    }
                                }
                    } catch (e: Throwable) {
                        emitter.onError(catchable(e))
                    }
                },
                BackpressureStrategy.LATEST
            )
            .subscribeOn(Schedulers.io())

    protected fun getFirestore(
        id: String,
        nullable: Throwable,
        error: (Throwable) -> Throwable,
        catchable: (Throwable) -> Throwable = error
    ): Single<Map<String, Any?>> =
        Single
            .create<Map<String, Any?>> { emitter ->
                try {
                    service.collection(collection)
                        .document(id)
                        .get(Source.SERVER)
                        .addOnSuccessListener { snapshot ->
                            when (val data = snapshot?.data) {
                                null -> emitter.onError(nullable)
                                else -> emitter.onSuccess(data.setIdentifier(id))
                            }
                        }
                        .addOnFailureListener { emitter.onError(error(it)) }
                } catch (e: Throwable) {
                    emitter.onError(catchable(e))
                }
            }
            .subscribeOn(Schedulers.io())

    protected fun getFirestore(
        field: String,
        value: String,
        nullable: Throwable,
        unique: Throwable,
        error: (Throwable) -> Throwable,
        catchable: (Throwable) -> Throwable = error
    ): Single<Map<String, Any?>> =
        Single
            .create<Map<String, Any?>> { emitter ->
                try {
                    service.collection(collection)
                        .whereEqualTo(field, value)
                        .get(Source.SERVER)
                        .addOnSuccessListener { snapshot ->
                            val documents = snapshot?.documents
                            when {
                                documents == null || documents.size < 1 -> emitter.onError(nullable)
                                documents.size > 1 -> emitter.onError(unique)
                                else -> {
                                    when (val data = documents[0]?.data) {
                                        null -> emitter.onError(nullable)
                                        else -> emitter.onSuccess(data.setIdentifier(documents[0].id))
                                    }
                                }
                            }
                        }
                        .addOnFailureListener { emitter.onError(error(it)) }
                } catch (e: Throwable) {
                    emitter.onError(catchable(e))
                }
            }
            .subscribeOn(Schedulers.io())

    protected fun createFirestore(
        single: Single<Map<String, Any?>>,
        error: (Throwable) -> Throwable,
        catchable: (Throwable) -> Throwable = error
    ): Completable =
        single
            .flatMapCompletable { data ->
                Completable
                    .create { emitter ->
                        try {
                            service.collection(collection)
                                .add(data.setCreatedAt())
                                .addOnSuccessListener { emitter.onComplete() }
                                .addOnFailureListener { e -> emitter.onError(error(e)) }
                        } catch (e: Throwable) {
                            emitter.onError(catchable(e))
                        }
                    }
                    .subscribeOn(Schedulers.io())
            }
            .observeOn(Schedulers.io())

    protected fun updateFirestore(
        single: Single<Map<String, Any?>>,
        parse: (Throwable) -> Throwable,
        error: (Throwable) -> Throwable = parse,
        catchable: (Throwable) -> Throwable = error
    ): Completable =
        single
            .map { it.popIdentifier(parse) }
            .flatMapCompletable { pair ->
                Completable
                    .create { emitter ->
                        try {
                            service.collection(collection)
                                .document(pair.first)
                                .update(pair.second.setUpdatedAt())
                                .addOnSuccessListener { emitter.onComplete() }
                                .addOnFailureListener { e -> emitter.onError(error(e)) }
                        } catch (e: Throwable) {
                            emitter.onError(catchable(e))
                        }
                    }
                    .subscribeOn(Schedulers.io())
            }
            .observeOn(Schedulers.io())

    protected fun deleteFirestore(
        single: Single<Map<String, Any?>>,
        parse: (Throwable) -> Throwable,
        error: (Throwable) -> Throwable = parse,
        catchable: (Throwable) -> Throwable = error
    ): Completable =
        single
            .map { it.popIdentifier(parse) }
            .flatMapCompletable { pair ->
                Completable
                    .create { emitter ->
                        try {
                            service.collection(collection)
                                .document(pair.first)
                                .update(pair.second.setDeletedAt())
                                .addOnSuccessListener { emitter.onComplete() }
                                .addOnFailureListener { e -> emitter.onError(error(e)) }
                        } catch (e: Throwable) {
                            emitter.onError(catchable(e))
                        }
                    }
                    .subscribeOn(Schedulers.io())
            }
            .observeOn(Schedulers.io())

    protected fun deleteFirestore(
        id: String,
        error: (Throwable) -> Throwable,
        catchable: (Throwable) -> Throwable = error
    ): Completable =
        Completable
            .create { emitter ->
                try {
                    service.collection(collection)
                        .document(id)
                        .delete()
                        .addOnSuccessListener { emitter.onComplete() }
                        .addOnFailureListener { e -> emitter.onError(error(e)) }
                } catch (e: Throwable) {
                    emitter.onError(catchable(e))
                }
            }
            .subscribeOn(Schedulers.io())

}