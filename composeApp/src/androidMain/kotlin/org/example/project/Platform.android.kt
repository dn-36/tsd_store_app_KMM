package org.example.project

import com.project.printer_barcode.VKPPrinter
import io.ktor.client.engine.okhttp.OkHttp
import networking.createHttpClient
import org.example.project.presentation.core.app.domain.SharedPrefsApi
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.Infrastructure.InfrastructurePrinterVkpImpl
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain.InfrastructurePrinterVkpAPI
import org.koin.core.module.Module
import org.koin.dsl.module
import shared_prefs.CreateContextPlatform
import shared_prefs.SharedPreferences


actual val platformModule: Module = module {
    factory {
        createHttpClient(OkHttp.create())
    }
    factory {
            SharedPreferences(get())
        }
        factory {
            CreateContextPlatform(get()).get()
        }
    factory {
        InfrastructurePrinterVkpImpl(get(),get()) as InfrastructurePrinterVkpAPI
    }
    factory { VKPPrinter() }

    }

