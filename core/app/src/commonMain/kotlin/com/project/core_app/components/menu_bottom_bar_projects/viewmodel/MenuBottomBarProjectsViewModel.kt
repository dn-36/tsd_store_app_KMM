package com.project.core_app.components.menu_bottom_bar_projects.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.project.network.Navigation

class MenuBottomBarProjectsViewModel : ViewModel() {

    var state by mutableStateOf(MenuBottomBarProjectsState( MenuBottomBarProjectsSection.PROJECTS_CONTROL ))

    private var setUsed:Boolean = false

    fun processIntent(intent: MenuBottomBarProjectsIntents){

        when(intent){

            is MenuBottomBarProjectsIntents.Projects -> { projects(intent.screen) }

            is MenuBottomBarProjectsIntents.ProjectsControl -> { projectsControl(intent.screen) }

            is MenuBottomBarProjectsIntents.SetScreen -> { if(!setUsed) {

                setUsed = true

                setScreen( intent.section ) } }
        }
    }

    fun projects(screen: Screen) {

        Navigation.navigator.push(screen)

    }

    fun projectsControl(screen:Screen) {

        Navigation.navigator.push(screen)

    }

    fun setScreen(section: MenuBottomBarProjectsSection){

        state  = state.copy(section)

    }

}