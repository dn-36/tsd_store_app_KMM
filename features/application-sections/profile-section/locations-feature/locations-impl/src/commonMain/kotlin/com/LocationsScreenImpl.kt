package com

import cafe.adriel.voyager.core.screen.Screen
import com.screen.LocationsScreen

class LocationsScreenImpl(): LocationsScreenApi {

    override fun locations(): Screen {

        return LocationsScreen()

    }

}