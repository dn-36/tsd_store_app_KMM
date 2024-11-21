package com.project.core_app.utils

fun convertDoubleToIntIfWhole(value: Double): Any {
    return if (value % 1 == 0.0) {
        value.toInt() // Преобразуем в Int, если дробная часть нулевая
    } else {
        value // Возвращаем как есть, если дробная часть не нулевая
    }
}