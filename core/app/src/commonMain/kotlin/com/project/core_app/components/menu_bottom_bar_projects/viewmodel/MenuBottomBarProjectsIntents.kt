package com.project.core_app.components.menu_bottom_bar_projects.viewmodel

import cafe.adriel.voyager.core.screen.Screen


sealed class MenuBottomBarProjectsIntents {

    data class Projects(val screen: Screen): MenuBottomBarProjectsIntents()

    data class ProjectsControl(val screen: Screen): MenuBottomBarProjectsIntents()

    data class SetScreen(val section: MenuBottomBarProjectsSection): MenuBottomBarProjectsIntents()

}