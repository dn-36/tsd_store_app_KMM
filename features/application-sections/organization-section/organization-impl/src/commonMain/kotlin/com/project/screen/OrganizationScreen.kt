package com.project.screen

import com.project.component.OrganizationComponent
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin


class OrganizationScreen : NetworkScreen(
    OrganizationComponent (getKoin().get())
)