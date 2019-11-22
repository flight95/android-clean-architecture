package pe.richard.architecture.boilerplate.presenter.model.user

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import org.reactivestreams.Publisher
import pe.richard.architecture.data.core.user.UserData

internal object UserMapper {

    internal class DomainSingleTransformer : SingleTransformer<User, UserData> {

        override fun apply(upstream: Single<User>): SingleSource<UserData> =
            upstream.map { transform(it) }

    }

    internal class DataSingleTransformer : SingleTransformer<UserData, User> {

        override fun apply(upstream: Single<UserData>): Single<User> =
            upstream.map { transform(it) }

    }

    internal class DataFlowableTransformer : FlowableTransformer<UserData, User> {

        override fun apply(upstream: Flowable<UserData>): Publisher<User> =
            upstream.map { transform(it) }

    }

    private fun transform(data: User): UserData =
        UserData(
            data.from,
            data.id,
            data.createdAt,
            data.updatedAt,
            data.deletedAt,
            data.email
        )

    private fun transform(data: UserData): User =
        User(
            data.from,
            data.id,
            data.createdAt,
            data.updatedAt,
            data.deletedAt,
            data.email
        )

}