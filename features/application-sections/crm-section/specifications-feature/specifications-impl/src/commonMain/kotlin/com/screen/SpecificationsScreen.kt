package com.screen

import com.component.SpecificationsComponent
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin


class SpecificationsScreen : NetworkScreen (

    SpecificationsComponent(getKoin().get())

)