package com.screen

import com.component.LocationsComponent
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin

class LocationsScreen: NetworkScreen (

    LocationsComponent(getKoin().get())

)