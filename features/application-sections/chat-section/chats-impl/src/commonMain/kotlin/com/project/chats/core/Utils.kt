package com.project.chats.core

import com.project.chats.screens.chats.domain.models.ChatsModel
import com.project.chats.screens.dialog.domain.models.DataEndTime

 object Utils {
     fun parseDateTimeManually(dateTimeString: String): DataEndTime {
         // Разделяем строку на дату и время
         val dateTimeParts = dateTimeString.split("T")

         // Дата — это первая часть
         val date = dateTimeParts[0]

         // Время содержит еще миллисекунды и "Z" в конце, их нужно удалить
         val timeWithMilliseconds = dateTimeParts[1].removeSuffix("Z")

         // Оставляем только часы и минуты (до первой двоеточия и после второй двоеточия)
         val time = timeWithMilliseconds.split(":").let { "${it[0]}:${it[1]}" }

         return DataEndTime(date = date, time = time)
     }


     fun sortByNearestDate(list: List<ChatsModel>): List<ChatsModel> {
         return list.sortedWith { a, b ->
             val (aDay, aMonth, aYear) = a.timeEndDate.date.split("-").map { it.toInt() }
             val (bDay, bMonth, bYear) = b.timeEndDate.date.split("-").map { it.toInt() }

             val (aHour, aMinute) = a.timeEndDate.time.split(":").map { it.toInt() }
             val (bHour, bMinute) = b.timeEndDate.time.split(":").map { it.toInt() }

             // Сравнение по году, месяцу, дню, часам и минутам
             when {
                 aYear != bYear -> bYear - aYear
                 aMonth != bMonth -> bMonth - aMonth
                 aDay != bDay -> bDay - aDay
                 aHour != bHour -> bHour - aHour
                 else -> bMinute - aMinute
             }
         }
     }
 }
