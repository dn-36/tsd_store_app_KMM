package com.project.project_conterol.component.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

@Composable
fun CustomCalendar(
    onDateSelected: (LocalDate) -> Unit
) {
    // Текущий месяц и год
    var selectedMonth by remember { mutableStateOf(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date) }

    // Список дней текущего месяца
    val daysInMonth = remember(selectedMonth) {
        val firstDayOfMonth = LocalDate(selectedMonth.year, selectedMonth.month, 1)
        val daysInMonth = selectedMonth.month.daysIn(selectedMonth.year)
        (1..daysInMonth).map { firstDayOfMonth.plus(it - 1, DateTimeUnit.DAY) }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Навигация между месяцами
        MonthNavigation(
            selectedMonth = selectedMonth,
            onMonthChanged = { selectedMonth = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Отображение дней недели
        DaysOfWeekRow()

        // Отображение дней месяца
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(daysInMonth.size) { index ->
                    val date = daysInMonth[index]
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable { onDateSelected(date) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = date.dayOfMonth.toString())
                    }
                }
            }
        )
    }
}

@Composable
fun MonthNavigation(
    selectedMonth: LocalDate,
    onMonthChanged: (LocalDate) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "<",
            modifier = Modifier.clickable { onMonthChanged(selectedMonth.minus(1, DateTimeUnit.MONTH)) }
        )
        Text(
            text = "${selectedMonth.month.name} ${selectedMonth.year}",
        )
        Text(
            text = ">",
            modifier = Modifier.clickable { onMonthChanged(selectedMonth.plus(1, DateTimeUnit.MONTH)) }
        )
    }
}

@Composable
fun DaysOfWeekRow() {
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        daysOfWeek.forEach { day ->
            Text(text = day, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
    }
}

fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

fun Month.daysIn(year: Int): Int {
    return when (this) {
        Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY, Month.AUGUST, Month.OCTOBER, Month.DECEMBER -> 31
        Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
        Month.FEBRUARY -> if (isLeapYear(year)) 29 else 28
        else -> throw IllegalArgumentException("Unknown month: $this")
    }
}