package vn.kamereo.time.entity

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val UTC_ZONE_STR = "UTC"

val UTC_ZONE_ID: ZoneId = ZoneId.of(UTC_ZONE_STR)
val ENGLISH_DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun getCurrentDay(): String = localDateTimeNow().format(ENGLISH_DATE_FORMATTER)
fun getDayBeforeToday(): String = localDateTimeNow().minusDays(1).format(ENGLISH_DATE_FORMATTER)

private fun localDateTimeNow(): LocalDateTime = LocalDateTime.now(UTC_ZONE_ID)
