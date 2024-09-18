package org.example.project.presentation.feature.qr_code_main.screens.core

import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain.ConectUSBUseCase
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain.PrintOnVkpUseCase
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.QRcodeMenuViewModel
import org.koin.dsl.module

val qrCodeFeatureModule = module{
    factory { QRcodeMenuViewModel(get(),get())  }
    factory { ConectUSBUseCase(get())  }
    factory { PrintOnVkpUseCase(get())  }

}