package vn.kamereo.entity.utils

import java.util.UUID

fun generateId(): String = UUID.randomUUID().toString()

data class Identifier<EntityType, RawType>(
    val rawValue: RawType
)
