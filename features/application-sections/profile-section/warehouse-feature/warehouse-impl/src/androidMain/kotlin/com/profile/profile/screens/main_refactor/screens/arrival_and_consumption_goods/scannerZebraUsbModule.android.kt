package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods

import org.koin.core.module.Module
import org.koin.dsl.module

actual val scannerZebraUsbModule: Module

    get() = module {

        factory { ScannerZebraUsbViewModel(get()) }

    }