package com.component.add_products.util

import kotlin.math.roundToLong

fun Double.formatForDisplay(): String {
    return if (this < 1e9) {
        // Округляем до двух знаков после запятой
        val roundedValue = (this * 100).roundToLong() / 100.0
        if (roundedValue % 1 == 0.0) {
            roundedValue.toLong().toString() // Если дробная часть = 0, возвращаем как целое число
        } else {
            roundedValue.toString() // Возвращаем как дробное число
        }
    } else {
        // Для больших чисел оставляем целое значение
        this.toLong().toString()
    }
}