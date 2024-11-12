package com.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class SpecificationsIntents {

    data class SetScreen( val coroutineScope: CoroutineScope): SpecificationsIntents()

    object Back : SpecificationsIntents()

    object BackFromDataEntry : SpecificationsIntents()

    data class OpenCreateDataEntry ( val coroutineScope: CoroutineScope ) : SpecificationsIntents()

}