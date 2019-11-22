package pe.richard.architecture.core.error

import androidx.annotation.StringRes
import pe.richard.architecture.core.R

sealed class UserError(
    type: Type,
    code: Int = 0,
    @StringRes resource: Int,
    cause: Throwable? = null
) : ApplicationError(code + Service.User.code + type.code, type, resource, cause) {

    class Unknown(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        UserError(Type.Unknown, code, resource ?: Type.Unknown.resource, cause)

    //region Network

    class Network(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        UserError(Type.Network, code, resource ?: Type.Network.resource, cause)

    //endregion

    //region Transform

    open class Transform(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        UserError(Type.Transform, code, resource ?: Type.Transform.resource, cause)

    class TransformIdentifier(cause: Throwable? = null) :
        Transform(1, R.string.error_user_transform_identifier, cause)

    class TransformEmail(cause: Throwable? = null) :
        Transform(1, R.string.error_user_transform_email, cause)

    class TransformCreatedAt(cause: Throwable? = null) :
        Transform(2, R.string.error_user_transform_created_at, cause)

    class TransformUpdatedAt(cause: Throwable? = null) :
        Transform(3, R.string.error_user_transform_updated_at, cause)

    class TransformDeletedAt(cause: Throwable? = null) :
        Transform(4, R.string.error_user_transform_deleted_at, cause)

    //endregion

    //region Get

    open class Get(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        UserError(Type.Get, code, resource ?: Type.Get.resource, cause)

    class ObserveFirestore(cause: Throwable? = null) :
        Get(1, R.string.error_user_observe_firestore, cause)

    class ObserveFirestoreNone(cause: Throwable? = null) :
        Get(2, R.string.error_user_observe_firestore_none, cause)

    open class GetFirestore(code: Int = 3, resource: Int = R.string.error_user_get_firestore, cause: Throwable? = null) :
        Get(code, resource, cause)

    class GetFirestoreMy(cause: Throwable? = null) :
        GetFirestore(4, R.string.error_user_get_firestore_my, cause)

    open class GetFirestoreNone(
        code: Int = 5,
        resource: Int = R.string.error_user_get_firestore_none,
        cause: Throwable? = null
    ) : Get(code, resource, cause)

    class GetFirestoreMyNone(cause: Throwable? = null) :
        GetFirestoreNone(6, R.string.error_user_get_firestore_my_none, cause)

    open class GetFirestoreUnique(
        code: Int = 7,
        resource: Int = R.string.error_user_get_firestore_unique,
        cause: Throwable? = null
    ) : Get(code, resource, cause)

    class GetFirestoreMyUnique(cause: Throwable? = null) :
        GetFirestoreUnique(8, R.string.error_user_get_firestore_my_unique, cause)

    //endregion

    //region Create

    open class Create(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        UserError(Type.Create, code, resource ?: Type.Create.resource, cause)

    open class CreateFirestore(
        code: Int = 1,
        resource: Int = R.string.error_user_create_firestore,
        cause: Throwable? = null
    ) : Create(code, resource, cause)

    class CreateFirestoreMy(cause: Throwable? = null) :
        CreateFirestore(2, R.string.error_user_create_firestore_my, cause)

    //endregion

    //region Update

    open class Update(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        UserError(Type.Update, code, resource ?: Type.Update.resource, cause)

    open class UpdateFirestore(
        code: Int = 1,
        resource: Int = R.string.error_user_update_firestore,
        cause: Throwable? = null
    ) : Update(code, resource, cause)

    class UpdateFirestoreMy(cause: Throwable? = null) :
        UpdateFirestore(2, R.string.error_user_update_firestore_my, cause)

    //endregion

    //region Delete

    open class Delete(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        UserError(Type.Delete, code, resource ?: Type.Delete.resource, cause)

    open class DeleteFirestore(
        code: Int = 1,
        resource: Int = R.string.error_user_delete_firestore,
        cause: Throwable? = null
    ) : Delete(code, resource, cause)

    class DeleteFirestoreMy(cause: Throwable? = null) :
        DeleteFirestore(2, R.string.error_user_delete_firestore, cause)

    class DeleteFirestoreIdentifier(cause: Throwable? = null) :
        DeleteFirestore(3, R.string.error_user_delete_firestore_identifier, cause)

    class DeleteFirestoreAuthentication(cause: Throwable? = null) :
        DeleteFirestore(4, R.string.error_user_delete_firestore_authentication, cause)


    //endregion
}