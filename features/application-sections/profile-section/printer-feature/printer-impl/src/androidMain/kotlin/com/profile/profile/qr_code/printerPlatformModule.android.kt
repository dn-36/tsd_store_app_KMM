package com.profile.profile.qr_code

import android.graphics.Bitmap
import com.project.core_app.usecases.ConectUSBUseCase
import com.project.core_app.usecases.ConnectToBleutoothDeviceUseCase
import com.project.core_app.usecases.GetListBluetoothDeviceUseCase
import com.project.core_app.usecases.GetQRcodeBitmapUseCase
import com.project.core_app.usecases.GetTitleProductBiteMapUseCase
import com.project.core_app.usecases.PrintOnTscUseCase
import com.project.core_app.usecases.PrintOnVkpUseCase
import com.project.core_app.usecases.StopBluetoothDiscovery
import com.project.core_app.viewmodel.QRcodeMenuViewModel
import com.project.phone.TSCprinter
import com.project.phone.VKPPrinter
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`Infrastructure-impl`.PrinterTscImpl
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.infrastructure.PrinterImpl
import org.koin.core.module.Module
import org.koin.dsl.module

actual val printerPlatformModule: Module
    get() = module {
        /* factory {
                 createHttpClient(OkHttp.create())
         }
         factory {
                 SharedPreferences(get())
             }
             factory {
                 CreateContextPlatform(get()).get()
             }*/
        factory {
            PrinterImpl(get(),get()) as InfrastructurePrinterVkpAPI
        }
        factory { PrinterTscImpl(get()) as InfrastructurePrinterTscAPI }

        single { TSCprinter(get()) }

        factory { VKPPrinter() }
        factory { QRcodeMenuViewModel(get(),get(),get(),get(),get(),get(),get(),get())  }
        factory { StopBluetoothDiscovery(get()) }
        factory { PrintOnTscUseCase<Bitmap>(get()) }
        factory { GetTitleProductBiteMapUseCase(get()) }
        factory { GetQRcodeBitmapUseCase(get())  }
        factory { ConectUSBUseCase(get())  }
        factory {  GetQRcodeBitmapUseCase(get()) }
        factory { PrintOnVkpUseCase(get())  }
        factory { ConnectToBleutoothDeviceUseCase(get()) }
        factory { GetListBluetoothDeviceUseCase(get()) }
    }
