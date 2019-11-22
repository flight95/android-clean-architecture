package pe.richard.architecture.data.remote.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import pe.richard.architecture.core.data.DateReferer
import pe.richard.architecture.core.data.extensions.getString
import java.util.Date

internal object RemoteFirestoreMapper {

    internal enum class Key(val value: String) {
        Identifier("_id"), // TODO: You should not use the keys used in Firestore.
        CreatedAt("created_at"),
        UpdatedAt("updated_at"),
        DeletedAt("deleted_at")
    }

    fun transform(referer: DateReferer): Map<String, Any?> =
        mapOf(
            Key.Identifier.value to referer.id,
            Key.CreatedAt.value to Timestamp(referer.createdAt),
            Key.UpdatedAt.value to Timestamp(referer.updatedAt)
        ).toMutableMap().apply {
            referer.deletedAt?.let { set(Key.DeletedAt.value, Timestamp(it)) }
        }

}

fun Map<String, Any?>.setIdentifier(id: String): Map<String, Any?> =
    toMutableMap().apply { set(RemoteFirestoreMapper.Key.Identifier.value, id) }

fun Map<String, Any?>.getIdentifier(parse: (Throwable) -> Throwable): String =
    getString(RemoteFirestoreMapper.Key.Identifier.value, parse)

fun Map<String, Any?>.popIdentifier(parse: (Throwable) -> Throwable): Pair<String, Map<String, Any?>> =
    Pair(
        getString(RemoteFirestoreMapper.Key.Identifier.value, parse),
        toMutableMap().apply { remove(RemoteFirestoreMapper.Key.Identifier.value) }
    )

fun Map<String, Any?>.setCreatedAt(): Map<String, Any?> =
    toMutableMap().apply {
        set(RemoteFirestoreMapper.Key.CreatedAt.value, FieldValue.serverTimestamp())
        set(RemoteFirestoreMapper.Key.UpdatedAt.value, FieldValue.serverTimestamp())
    }

fun Map<String, Any?>.getCreatedAt(parse: (Throwable) -> Throwable): Date =
    getDate(RemoteFirestoreMapper.Key.CreatedAt.value, parse)

fun Map<String, Any?>.setUpdatedAt(): Map<String, Any?> =
    toMutableMap().apply {
        set(RemoteFirestoreMapper.Key.UpdatedAt.value, FieldValue.serverTimestamp())
    }

fun Map<String, Any?>.getUpdatedAt(parse: (Throwable) -> Throwable): Date =
    getDate(RemoteFirestoreMapper.Key.UpdatedAt.value, parse)

fun Map<String, Any?>.setDeletedAt(): Map<String, Any?> =
    toMutableMap().apply {
        set(RemoteFirestoreMapper.Key.UpdatedAt.value, FieldValue.serverTimestamp())
        set(RemoteFirestoreMapper.Key.DeletedAt.value, FieldValue.serverTimestamp())
    }

fun Map<String, Any?>.getDeletedAt(parse: (Throwable) -> Throwable): Date? =
    getDateNullable(RemoteFirestoreMapper.Key.DeletedAt.value, parse)

fun Map<String, Any?>.getDate(key: String, error: (cause: Throwable) -> Throwable): Date =
    getDateNullable(key, error) ?: throw error(IllegalArgumentException("The value of $key must not be null."))

fun Map<String, Any?>.getDateNullable(key: String, error: (cause: Throwable) -> Throwable): Date? =
    try {
        when (val value = get(key)) {
            null -> null
            else -> (value as Timestamp).toDate()
        }
    } catch (cause: Throwable) {
        throw error(cause)
    }