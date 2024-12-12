package com.project.phone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import device.common.DecodeResult
import device.common.DecodeStateCallback
import device.common.ScanConst
import device.sdk.ScanManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ScanerPointMobileViewModel(
    private val context: Context,
    private val scanner: ScanManager = ScanManager()
)  {

    private val _uiState = MutableStateFlow(ScanerPointMobileState())
    val uiState: StateFlow<ScanerPointMobileState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<ScanerPointMobileEffect>()
    val effect: SharedFlow<ScanerPointMobileEffect> = _effect.asSharedFlow()

    private var backupResultType = ScanConst.ResultType.DCD_RESULT_COPYPASTE

    // Callback для изменения состояния сканера
    private val stateCallback = object : DecodeStateCallback() {
        override fun onChangedState(state: Int) {
            handleEvent(MainEvent.OnScannerStateChanged(state))
        }
    }

    // Receiver для получения результатов сканирования
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(c: Context?, intent: Intent?) {
            if (intent == null) return
            when (intent.action) {
                ScanConst.INTENT_USERMSG -> {
                    try {
                        val decodeResult = DecodeResult()
                        scanner.aDecodeGetResult(decodeResult.recycle())
                        val barType = decodeResult.symName
                        val result = decodeResult.toString()
                        handleEvent(MainEvent.OnScanResult(barType, result))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                ScanConst.INTENT_EVENT -> {
                    val decodeValueBytes = intent.getByteArrayExtra(ScanConst.EXTRA_EVENT_DECODE_VALUE)
                    val decodeLength = intent.getIntExtra(ScanConst.EXTRA_EVENT_DECODE_LENGTH, 0)
                    val decodeValue = if (decodeValueBytes != null && decodeLength > 0) {
                        String(decodeValueBytes, 0, decodeLength)
                    } else {
                        ""
                    }
                    val symbolName = intent.getStringExtra(ScanConst.EXTRA_EVENT_SYMBOL_NAME) ?: ""

                    handleEvent(MainEvent.OnScanResult(symbolName, decodeValue))
                }
            }
        }
    }

    init {
        // Инициализация
        val isPMModel = _uiState.value.isPMModel
        if (isPMModel) {
    //        viewModelScope.launch {
       //         delay(1000)
                scanner.aDecodeSetResultType(ScanConst.ResultType.DCD_RESULT_EVENT)
                initScanner()
    //        }

            // Регистрация callback и receiver
            scanner.aRegisterDecodeStateCallback(stateCallback)
            val filter = IntentFilter().apply {
                addAction(ScanConst.INTENT_USERMSG)
                addAction(ScanConst.INTENT_EVENT)
            }
            context.registerReceiver(receiver, filter)
        }
    }


     fun onCleared() {
       // super.onCleared()
        val isPMModel = _uiState.value.isPMModel
        if (isPMModel) {
            scanner.aUnregisterDecodeStateCallback(stateCallback)
            context.unregisterReceiver(receiver)
            // Восстановим тип результата
            scanner.aDecodeSetResultType(backupResultType)
        }
    }

    private fun initScanner() {
        val currentState = uiState.value
        backupResultType = scanner.aDecodeGetResultType()

        // Настраиваем тип результата
        if (currentState.eventEnabled) {
            scanner.aDecodeSetResultType(ScanConst.ResultType.DCD_RESULT_EVENT)
        } else {
            scanner.aDecodeSetResultType(ScanConst.ResultType.DCD_RESULT_USERMSG)
        }

        // Режим триггера
        if (currentState.autoScanEnabled) {
            scanner.aDecodeSetTriggerMode(ScanConst.TriggerMode.DCD_TRIGGER_MODE_AUTO)
        } else {
            scanner.aDecodeSetTriggerMode(ScanConst.TriggerMode.DCD_TRIGGER_MODE_ONESHOT)
        }

        // Звук
        scanner.aDecodeSetBeepEnable(if (currentState.beepEnabled) 1 else 0)
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ToggleAutoScan -> {
                val newAutoScan = !_uiState.value.autoScanEnabled
                _uiState.update { it.copy(autoScanEnabled = newAutoScan) }
                if (_uiState.value.isPMModel) {
                    if (newAutoScan) {
                        scanner.aDecodeSetTriggerMode(ScanConst.TriggerMode.DCD_TRIGGER_MODE_AUTO)
                    } else {
                        scanner.aDecodeSetTriggerMode(ScanConst.TriggerMode.DCD_TRIGGER_MODE_ONESHOT)
                    }
                }
            }

            is MainEvent.ScanOn -> {
                if (_uiState.value.isPMModel) {
                    scanner.aDecodeSetTriggerOn(1)
                }
            }

            is MainEvent.ScanOff -> {
                if (_uiState.value.isPMModel) {
                    val wasAuto = _uiState.value.autoScanEnabled
                    if (wasAuto) {
                        scanner.aDecodeSetTriggerMode(ScanConst.TriggerMode.DCD_TRIGGER_MODE_ONESHOT)
                    }
                    scanner.aDecodeSetTriggerOn(0)
                    if (wasAuto) {
                        scanner.aDecodeSetTriggerMode(ScanConst.TriggerMode.DCD_TRIGGER_MODE_AUTO)
                    }
                }
            }

            is MainEvent.DismissDialog -> {
                _uiState.update { it.copy(showEnableDialog = false) }
            }

            is MainEvent.EnableScannerClicked -> {
                event.scope .launch {
                    _effect.emit(ScanerPointMobileEffect.OpenScannerSettings)
                }
            }

            is MainEvent.OnScanResult -> {
                _uiState.update { it.copy(barType = event.barType, scanResult = event.result) }
            }

            is MainEvent.OnScannerStateChanged -> {
                when (event.state) {
                    ScanConst.STATE_ON, ScanConst.STATE_TURNING_ON -> {
                        _uiState.update { it.copy(showEnableDialog = false) }
                    }
                    ScanConst.STATE_OFF, ScanConst.STATE_TURNING_OFF -> {
                        _uiState.update { it.copy(showEnableDialog = true) }
                    }
                }
            }
        }
    }
}