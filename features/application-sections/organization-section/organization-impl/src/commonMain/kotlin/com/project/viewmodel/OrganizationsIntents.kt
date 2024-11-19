package com.project.viewmodel

import com.project.network.organizations_network.model.Response
import kotlinx.coroutines.CoroutineScope

sealed class OrganizationsIntents {
    data class SetScreen ( val coroutineScope: CoroutineScope ): OrganizationsIntents()
    data class SelectItemUpdate ( val item: Response ): OrganizationsIntents()
    data class UpdateOrganization ( val coroutineScope: CoroutineScope,val name: String,val url: String, val ui: String ): OrganizationsIntents()
    data class ChoosingActiveOrganization ( val coroutineScope: CoroutineScope,val ui:String ): OrganizationsIntents()
    data class DeleteOrganization ( val coroutineScope: CoroutineScope ): OrganizationsIntents()
    data class CreateOrganization ( val coroutineScope: CoroutineScope,val name: String,val url: String ): OrganizationsIntents()
    object OpenWindowAddOrganization: OrganizationsIntents()
    object CancelOrganizationComponent: OrganizationsIntents()
    object NoDelete: OrganizationsIntents()
    data class OpenDeleteComponent ( val item: Response ): OrganizationsIntents()
    data class InputTextSearchComponent( val text: String ): OrganizationsIntents()
}