package pe.richard.architecture.boilerplate.presenter.model.auth

import android.net.Uri
import pe.richard.architecture.core.data.From

data class Auth(
    val from: From,
    val id: String,
    val email: String,
    val familyName: String,
    val firstName: String,
    val thumbnail: Uri? = null
)