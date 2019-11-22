package pe.richard.architecture.data.core.user

import pe.richard.architecture.core.data.DateReferer
import pe.richard.architecture.core.data.From
import java.util.Date

data class UserData(
    override val from: From,
    override val id: String,
    override val createdAt: Date,
    override val updatedAt: Date,
    override val deletedAt: Date? = null,
    val email: String
) : DateReferer()