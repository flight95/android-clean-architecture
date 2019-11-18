package pe.richard.architecture.core.rx.extensions

import io.reactivex.Completable

fun Completable.flatMap(other: () -> Completable): Completable =
    toSingleDefault(true)
        .flatMapCompletable { other() }