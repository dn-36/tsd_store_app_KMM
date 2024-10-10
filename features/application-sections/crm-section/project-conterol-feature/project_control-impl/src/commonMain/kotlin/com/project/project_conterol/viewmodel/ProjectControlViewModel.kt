package org.example.project.presentation.project_control.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProjectControlViewModel:ViewModel() {
    var projectControlState by mutableStateOf(ProjectControlState())
}