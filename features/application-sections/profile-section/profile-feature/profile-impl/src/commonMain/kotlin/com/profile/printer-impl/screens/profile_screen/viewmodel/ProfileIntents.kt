package org.example.project.presentation.profile_feature.main_feature.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class ProfileIntents {
    object Warehouse:ProfileIntents()

    data class SetScreen(val coroutineScope: CoroutineScope):ProfileIntents()
}