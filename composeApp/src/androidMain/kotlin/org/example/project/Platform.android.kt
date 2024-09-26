package org.example.project

import android.graphics.Bitmap
import com.project.printer_barcode.VKPPrinter
import io.ktor.client.engine.okhttp.OkHttp
import authorization_network.createHttpClient
import com.project.printer_barcode.TSCprinter
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.Infrastructure.PrinterImpl
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`Infrastructure-impl`.PrinterTscImpl
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.ConectUSBUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.ConnectToBleutoothDeviceUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetListBluetoothDeviceUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetQRcodeBitmapUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetTitleProductBiteMapUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.PrintOnTscUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.PrintOnVkpUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.StopBluetoothDiscovery
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI
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
        PrinterImpl(get(),get()) as InfrastructurePrinterVkpAPI
    }
    factory { PrinterTscImpl(get()) as InfrastructurePrinterTscAPI }

    single {TSCprinter(get())}

    factory { VKPPrinter() }
    factory { QRcodeMenuViewModel(get(),get(),get(),get(),get(),get(),get(),get())  }
    factory { StopBluetoothDiscovery(get()) }
    factory { PrintOnTscUseCase<Bitmap>(get()) }
    factory { GetTitleProductBiteMapUseCase(get()) }
    factory { GetQRcodeBitmapUseCase(get())  }
    factory { ConectUSBUseCase(get())  }
    factory {  GetQRcodeBitmapUseCase(get())}
    factory { PrintOnVkpUseCase(get())  }
    factory { ConnectToBleutoothDeviceUseCase(get()) }
    factory { GetListBluetoothDeviceUseCase(get()) }
    }

