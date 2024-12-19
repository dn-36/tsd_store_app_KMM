package org.example.project.presentation.profile_feature.main_feature.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class ProfileIntents {

    object Warehouse: ProfileIntents()

    object Projects: ProfileIntents()

    object Contragents: ProfileIntents()

    object Specifications: ProfileIntents()

    object Notes: ProfileIntents()

    object Tools: ProfileIntents()

    object ProductCategories: ProfileIntents()

    data class SetScreen(val coroutineScope: CoroutineScope): ProfileIntents()
}