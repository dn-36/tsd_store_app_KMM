package com.arrival_and_consumption_goods

import com.arrival_and_consumption_goods.viewmodel.ScannerZebraUsbViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val scannerZebraUsbModule: Module

    get() = module {

        factory { ScannerZebraUsbViewModel(get()) }

    }