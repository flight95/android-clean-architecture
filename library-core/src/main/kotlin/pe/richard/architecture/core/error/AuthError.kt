package pe.richard.architecture.core.error

import androidx.annotation.StringRes
import pe.richard.architecture.core.R

sealed class AuthError(
    type: Type,
    code: Int = 0,
    @StringRes resource: Int,
    cause: Throwable? = null
) : ApplicationError(code + Service.Auth.code + type.code, type, resource, cause) {

    class Unknown(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        AuthError(Type.Unknown, code, resource ?: Type.Unknown.resource, cause)

    //region Network

    class Network(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        AuthError(Type.Network, code, resource ?: Type.Network.resource, cause)

    //endregion

    //region Transform

    open class Transform(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        AuthError(Type.Transform, code, resource ?: Type.Transform.resource, cause)

    class TransformEmail :
        Transform(1, R.string.error_authentication_transform_email)

    class TransformFamilyName :
        Transform(2, R.string.error_authentication_transform_family_name)

    class TransformGivenName :
        Transform(3, R.string.error_authentication_transform_given_name)

    //endregion

    //region Get

    open class Get(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        AuthError(Type.Get, code, resource ?: Type.Get.resource, cause)

    class GetFirebase(cause: Throwable? = null) :
        Get(1, R.string.error_authentication_get_firebase, cause)

    class GetGoogle(cause: Throwable? = null) :
        Get(2, R.string.error_authentication_get_google, cause)

    class GetGoogleCancel(cause: Throwable? = null) :
        Get(3, R.string.error_authentication_get_google_cancel, cause)

    class GetGoogleClient(cause: Throwable? = null) :
        Get(4, R.string.error_authentication_get_google, cause)

    //endregion

    //region Delete

    open class Delete(code: Int = 0, resource: Int? = null, cause: Throwable? = null) :
        AuthError(Type.Delete, code, resource ?: Type.Delete.resource, cause)

    class DeleteFirebase(cause: Throwable? = null) :
        Delete(1, R.string.error_authentication_delete_firebase, cause)

    class DeleteGoogle(cause: Throwable? = null) :
        Delete(2, R.string.error_authentication_delete_google, cause)

    //endregion

}