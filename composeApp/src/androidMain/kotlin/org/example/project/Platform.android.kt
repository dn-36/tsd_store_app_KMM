package org.example.project

import com.project.printer_barcode.VKPPrinter
import io.ktor.client.engine.okhttp.OkHttp
import authorization_network.createHttpClient
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.Infrastructure.PrinterVkpImpl
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.InfrastructurePrinterVkpAPI
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.ConectUSBUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetQRcodeBitmapUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetTitleProductBiteMapUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.PrintOnVkpUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel.QRcodeMenuViewModel
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
        PrinterVkpImpl(get(),get()) as InfrastructurePrinterVkpAPI
    }
    factory { VKPPrinter() }
    factory { QRcodeMenuViewModel(get(),get(),get(),get())  }
    factory { GetTitleProductBiteMapUseCase(get()) }
    factory { GetQRcodeBitmapUseCase(get())  }
    factory { ConectUSBUseCase(get())  }
    factory {  GetQRcodeBitmapUseCase(get())}
    factory { PrintOnVkpUseCase(get())  }
    }

