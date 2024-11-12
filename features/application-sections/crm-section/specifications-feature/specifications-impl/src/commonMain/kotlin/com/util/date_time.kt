package com.util

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun formatDateTime(dateTime: String): String {
    // Преобразуем строку в Instant (момент времени)
    val instant = Instant.parse(dateTime)

    // Преобразуем Instant в LocalDateTime с использованием часового пояса UTC
    val dateTimeUtc = instant.toLocalDateTime(TimeZone.UTC)

    // Получаем год, месяц и день
    val year = dateTimeUtc.year
    val month = dateTimeUtc.monthNumber
    val day = dateTimeUtc.dayOfMonth

    // Форматируем дату в строку
    return "$day.$month.$year"
}