package org.example.project.presentation.profile_feature.main_feature.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ProfileState(

    val isUsed : MutableState<Boolean> = mutableStateOf(true),
    val name : String = "",
    val numberPhone : String = "",

)
