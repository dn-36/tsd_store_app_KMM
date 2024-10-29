package org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel

import androidx.compose.ui.graphics.Color

data class MenuBottomBarWarehouseState(

    var section: MenuBottomBarWarehouseSection

)

enum class MenuBottomBarWarehouseSection(

    val WarehouseButtonCollor:Color,

    val FinanceButtonCollor:Color,

    val PrintButtonCollor:Color,

    ) {

    FINANCE(Color.White,Color(0xFFFF9800), Color.White),

    PRINT(Color.White, Color.White, Color(0xFFFF9800)),

    WAREHOUSE ( Color(0xFFFF9800), Color.White, Color.White )

}
