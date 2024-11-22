package com

import cafe.adriel.voyager.core.screen.Screen
import com.screen.SpecificationsScreen

class SpecificationsScreenImpl: SpecificationsScreenApi {

    override fun specifications(): Screen {

        return SpecificationsScreen()

    }

}