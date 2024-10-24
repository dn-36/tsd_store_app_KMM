package org.example.project.core.menu_bottom_bar.viewmodel

import androidx.compose.ui.graphics.Color

data class MenuBottomBarState(
    var section: MenuBottomBarSection
)

enum class MenuBottomBarSection(
    val OrganizationButtonCollor:Color,
    val CrmButtonCollor:Color,
    val TapeButtonCollor:Color,
    val ChutsButtonCollor:Color,
    val ProfileButtonCollor:Color,
    ) {
    ORGANIZATION(Color.Yellow , Color.White,
        Color.White, Color.White, Color.White),
    CRM(Color.White, Color.Yellow,
        Color.White, Color.White, Color.White),
    TAPE(Color.White, Color.White,
        Color.Yellow, Color.White, Color.White),
    CHATS(Color.White, Color.White,
        Color.White, Color.Yellow, Color.White),
    PROFILE(Color.White, Color.White,
        Color.White, Color.White, Color.Yellow)
}
