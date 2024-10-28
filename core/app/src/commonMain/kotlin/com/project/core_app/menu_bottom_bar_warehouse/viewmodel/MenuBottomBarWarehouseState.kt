package org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel

import androidx.compose.ui.graphics.Color

data class MenuBottomBarWarehouseState(

    var section: MenuBottomBarWarehouseSection

)

enum class MenuBottomBarWarehouseSection(

    val PrintButtonCollor:Color,

    val FinanceButtonCollor:Color,

    val WarehouseButtonCollor:Color,

    val ProfileButtonCollor:Color,

    ) {
    PROFILE( Color.White,

        Color.White, Color.White, Color(0xFFFF9800)),

    FINANCE(Color.White,Color(0xFFFF9800), Color.White,

         Color.White),

    PRINT(Color.White, Color.White, Color(0xFFFF9800),

        Color.White ),

    WAREHOUSE ( Color(0xFFFF9800), Color.White, Color.White,

        Color.White )

}
