package com.project.core_app

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun getFormattedDateTime(): Date {
    // Получаем текущее время в UTC
    val currentMoment: Instant = Clock.System.now()

    // Преобразуем его в локальное время
    val dateTime: LocalDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

    // Форматируем строку в нужный формат
    val date = dateTime.date
    val time = dateTime.time

    return Date(date.toString(),
            "${time.hour.toString().padStart(2, '0')}:${time.minute.toString().padStart(2, '0')}")
}

data class Date(
    val time:String,
    val date:String
)
