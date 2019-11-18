package pe.richard.architecture.boilerplate.presenter.model.auth

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import pe.richard.architecture.data.core.auth.AuthData

internal object AuthMapper {

    class DataSingleTransformer : SingleTransformer<AuthData, Auth> {

        override fun apply(upstream: Single<AuthData>): Single<Auth> =
            upstream.map {
                Auth(
                    it.from,
                    it.id,
                    it.email,
                    it.familyName,
                    it.firstName,
                    it.thumbnail
                )
            }

    }

    class DataFlowableTransformer : FlowableTransformer<AuthData, Auth> {

        override fun apply(upstream: Flowable<AuthData>): Flowable<Auth> =
            upstream.map {
                Auth(
                    it.from,
                    it.id,
                    it.email,
                    it.familyName,
                    it.firstName,
                    it.thumbnail
                )
            }

    }

}