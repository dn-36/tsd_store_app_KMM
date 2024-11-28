package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.scanner_component

import TsdScannerApi
import org.koin.mp.KoinPlatform

class ScannerViewModel {

    fun processIntents ( intent: ScannerIntents ) {

        when ( intent ) {

            is ScannerIntents.TsdScanner -> tsdScanner()

        }

    }

    fun tsdScanner(){

        val tsdScanner: TsdScannerApi = KoinPlatform.getKoin().get()

        tsdScanner.scanWithUsbScanner()

    }

}