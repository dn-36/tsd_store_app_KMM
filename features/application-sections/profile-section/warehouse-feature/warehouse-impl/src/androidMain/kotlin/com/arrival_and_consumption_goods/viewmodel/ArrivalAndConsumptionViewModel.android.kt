package com.arrival_and_consumption_goods.viewmodel

import cafe.adriel.voyager.core.screen.Screen
import com.project.network.Navigation
import com.project.phone.ScanerPointMobileScreen


actual val goToPointMobile: ((String)->Unit) -> Unit
    get() = {
        Navigation.navigator.push(ScanerPointMobileScreen(it))
    }