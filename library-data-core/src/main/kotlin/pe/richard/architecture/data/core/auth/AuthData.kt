package pe.richard.architecture.data.core.auth

import android.net.Uri
import pe.richard.architecture.core.data.From
import pe.richard.architecture.core.data.IdReferer

data class AuthData(
    override val from: From,
    override val id: String,
    val email: String,
    val familyName: String,
    val firstName: String,
    val thumbnail: Uri? = null
) : IdReferer()