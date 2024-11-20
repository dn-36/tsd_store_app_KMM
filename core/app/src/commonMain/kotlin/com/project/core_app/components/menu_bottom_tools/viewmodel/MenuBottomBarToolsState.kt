package com.project.core_app.components.menu_bottom_tools.viewmodel

import androidx.compose.ui.graphics.Color

data class MenuBottomBarToolsState(

    var section: MenuBottomBarToolsSection

)

enum class MenuBottomBarToolsSection(

    val ToolsButtonCollor:Color,

    val PrintButtonCollor:Color,

    ) {

    Camera(Color(0xFFFF9800),Color.White),

    PRINT(Color.White,Color(0xFFFF9800)),


}
