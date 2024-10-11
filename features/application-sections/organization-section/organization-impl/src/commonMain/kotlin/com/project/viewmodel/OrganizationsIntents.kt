package com.project.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class OrganizationsIntents {
    data class SetScreen(val coroutineScope: CoroutineScope):OrganizationsIntents()
}