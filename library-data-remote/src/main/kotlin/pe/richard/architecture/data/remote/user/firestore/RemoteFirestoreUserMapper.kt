package pe.richard.architecture.data.remote.user.firestore

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import org.reactivestreams.Publisher
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.data.extensions.getString
import pe.richard.architecture.core.error.UserError
import pe.richard.architecture.data.core.user.UserData
import pe.richard.architecture.data.remote.firestore.RemoteFirestoreMapper
import pe.richard.architecture.data.remote.firestore.getCreatedAt
import pe.richard.architecture.data.remote.firestore.getDeletedAt
import pe.richard.architecture.data.remote.firestore.getIdentifier
import pe.richard.architecture.data.remote.firestore.getUpdatedAt

internal object RemoteFirestoreUserMapper {

    internal enum class Key(val value: String) {
        AuthId("id"), // from AuthData.
        Email("email") // TODO: Use only when creating.
    }

    internal class RemoteSingleTransformer : SingleTransformer<UserData, Map<String, Any?>> {

        override fun apply(upstream: Single<UserData>): SingleSource<Map<String, Any?>> =
            upstream.map { transform(it) }

    }

    internal class DataSingleTransformer(private val from: From) : SingleTransformer<Map<String, Any?>, UserData> {

        override fun apply(upstream: Single<Map<String, Any?>>): SingleSource<UserData> =
            upstream.map { transform(from, it) }

    }

    internal class DataFlowableTransformer(private val from: From) : FlowableTransformer<Map<String, Any?>, UserData> {

        override fun apply(upstream: Flowable<Map<String, Any?>>): Publisher<UserData> =
            upstream.map { transform(from, it) }

    }

    private fun transform(data: UserData): Map<String, Any?> =
        RemoteFirestoreMapper.transform(data)
            .plus(
                mapOf(
                    // TODO: Add code when UserData field will be changed.
                )
            )

    private fun transform(from: From, data: Map<String, Any?>): UserData =
        UserData(
            from,
            data.getIdentifier { UserError.TransformIdentifier(it) },
            data.getCreatedAt { UserError.TransformCreatedAt(it) },
            data.getUpdatedAt { UserError.TransformUpdatedAt(it) },
            data.getDeletedAt { UserError.TransformDeletedAt(it) },
            data.getString(Key.Email.value) { UserError.TransformEmail(it) }
        )

}