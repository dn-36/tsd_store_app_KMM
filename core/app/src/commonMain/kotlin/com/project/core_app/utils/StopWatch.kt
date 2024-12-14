package com.project.core_app.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class Stopwatch() {
    private var timerJob: Job? = null
    private val _timeFlow = MutableStateFlow("00:00") // Время в формате "мм:сс"
    val timeFlow: StateFlow<String> get() = _timeFlow

    private var elapsedSeconds: Long = 0 // Счетчик времени в секундах

    // Функция старта секундомера
    fun start(scope: CoroutineScope) {
        if (timerJob == null || timerJob?.isCancelled == true) {
            timerJob = scope.launch(Dispatchers.Default) {
                while (isActive) {
                    delay(1000L) // Ждем 1 секунду
                    elapsedSeconds += 1 // Увеличиваем счетчик времени
                    _timeFlow.value = formatTime(elapsedSeconds) // Обновляем форматированное время
                }
            }
        }
    }

    // Функция остановки секундомера
    fun stop() {
        timerJob?.cancel() // Отменяем корутину
        timerJob = null
    }

    // Функция сброса времени
    fun reset() {
        stop() // Останавливаем секундомер
        elapsedSeconds = 0 // Сбрасываем счетчик времени
        _timeFlow.value = formatTime(elapsedSeconds) // Сбрасываем форматированное время
    }


    private fun formatTime(seconds: Long): String {
        val minutes = (seconds / 60).toString().padStart(2, '0') // Форматируем минуты
        val secs = (seconds % 60).toString().padStart(2, '0')   // Форматируем секунды
        return "$minutes:$secs"
    }
}
