package pe.richard.architecture.core.data.extensions

fun Map<String, Any?>.getString(key: String, error: (cause: Throwable) -> Throwable): String =
    getStringNullable(key, error) ?: throw error(IllegalArgumentException("The value of $key must not be null."))

fun Map<String, Any?>.getStringNullable(key: String, error: (cause: Throwable) -> Throwable): String? =
    try {
        when (val value = get(key)) {
            null -> null
            else -> value as String
        }
    } catch (cause: Throwable) {
        throw error(cause)
    }