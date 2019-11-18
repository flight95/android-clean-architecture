package pe.richard.architecture.core.rx.extensions

import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

fun <T : Any, U : Any, R : Any> Single<T>.flatMap(
    other: Single<U>,
    zipper: (T, U) -> R
): Single<R> =
    flatMap { first ->
        other.map { second ->
            zipper(first, second)
        }
    }

fun <T : Any, R : Any> Single<T>.flatMap(
    scheduler: Scheduler,
    other: (T, SingleEmitter<R>) -> Unit
): Single<R> =
    flatMap {
        Single.create<R> { emitter -> other(it, emitter) }
            .subscribeOn(scheduler)
    }

fun <T : Any> Single<T>.flatMapCompletable(
    scheduler: Scheduler,
    other: (T, CompletableEmitter) -> Unit
): Completable =
    flatMapCompletable {
        Completable.create { emitter -> other(it, emitter) }
            .subscribeOn(scheduler)
    }
