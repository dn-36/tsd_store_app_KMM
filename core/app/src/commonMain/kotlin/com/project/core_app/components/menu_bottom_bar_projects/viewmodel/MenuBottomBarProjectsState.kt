package com.project.core_app.components.menu_bottom_bar_projects.viewmodel

import androidx.compose.ui.graphics.Color

data class MenuBottomBarProjectsState(

    var section: MenuBottomBarProjectsSection

)

enum class MenuBottomBarProjectsSection(

    val ControlProjectsButtonCollor:Color,

    val ProjectsButtonCollor:Color

    ) {

    PROJECTS_CONTROL(Color(0xFFFF9800), Color.White),

    PROJECTS ( Color.White, Color(0xFFFF9800) ),

}
