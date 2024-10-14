package com.project.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class OrganizationsIntents {
    data class SetScreen(val coroutineScope: CoroutineScope):OrganizationsIntents()
    data class ChoosingActiveOrganization(val coroutineScope: CoroutineScope,val ui:String):OrganizationsIntents()
    data class DeleteOrganization(val coroutineScope: CoroutineScope,val ui:String):OrganizationsIntents()
    data class CreateOrganization(val coroutineScope: CoroutineScope,val name: String,val url: String):OrganizationsIntents()
    object AddOrganizationNavigate:OrganizationsIntents()
}