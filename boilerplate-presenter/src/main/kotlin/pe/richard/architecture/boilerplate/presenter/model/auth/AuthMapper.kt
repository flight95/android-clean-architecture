package pe.richard.architecture.boilerplate.presenter.model.auth

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import org.reactivestreams.Publisher
import pe.richard.architecture.data.core.auth.AuthData

internal object AuthMapper {

    internal class DataSingleTransformer : SingleTransformer<AuthData, Auth> {

        override fun apply(upstream: Single<AuthData>): Single<Auth> =
            upstream.map { transform(it) }

    }

    internal class DataFlowableTransformer : FlowableTransformer<AuthData, Auth> {

        override fun apply(upstream: Flowable<AuthData>): Publisher<Auth> =
            upstream.map { transform(it) }

    }

    private fun transform(data: AuthData): Auth =
        Auth(
            data.from,
            data.id,
            data.email,
            data.familyName,
            data.firstName,
            data.thumbnail
        )

}