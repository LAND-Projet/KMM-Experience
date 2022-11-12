package com.dardev.yanotekmm.data

import com.dardev.yanotekmm.domain.yanote.Yanote
import database.YanoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun YanoteEntity.toYanote(): Yanote {
    return Yanote(
        id = id,
        title = title,
        content = content,
        colorHex = colorHex,
        created = Instant
            .fromEpochMilliseconds(created)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}