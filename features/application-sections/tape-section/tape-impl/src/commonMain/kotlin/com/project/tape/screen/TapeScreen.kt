package com.project.tape.screen

import com.project.core_app.network_base_screen.NetworkScreen
import com.project.tape.component.TapeComponent
import org.koin.mp.KoinPlatform

class TapeScreen (): NetworkScreen(

    TapeComponent(KoinPlatform.getKoin().get())

)