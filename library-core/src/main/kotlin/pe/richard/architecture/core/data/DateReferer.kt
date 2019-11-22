package pe.richard.architecture.core.data

import java.util.Date

abstract class DateReferer : IdReferer() {

    abstract val createdAt: Date
    abstract val updatedAt: Date
    abstract val deletedAt: Date?

}