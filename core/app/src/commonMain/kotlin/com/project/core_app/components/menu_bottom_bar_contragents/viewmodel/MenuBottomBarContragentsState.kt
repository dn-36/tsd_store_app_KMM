package com.project.core_app.components.menu_bottom_bar_contragents.viewmodel

import androidx.compose.ui.graphics.Color

data class MenuBottomBarContragentsState(

    var section: MenuBottomBarContragentsSection

)

enum class MenuBottomBarContragentsSection(

    val ContragentsButtonCollor:Color,

    val LocationsButtonCollor:Color

    ) {

    CONTRAGENTS ( Color(0xFFFF9800),Color.White ),

    LOCATIONS (Color.White,Color(0xFFFF9800)),


}
