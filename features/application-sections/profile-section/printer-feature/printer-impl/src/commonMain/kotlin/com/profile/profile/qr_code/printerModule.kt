package com.profile.profile.qr_code

import com.project.`printer-api`.PrinterScreensApi
import org.koin.dsl.module

val printerScreenModule = module {
    factory {
        PrinterScreensImpl() as PrinterScreensApi
    }

}