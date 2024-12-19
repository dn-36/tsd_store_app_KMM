package com.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.CatalogProductsScreenApi
import com.domain.usecases.GeListCategoriesUseCase
import com.project.chats.ProfileScreensApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class ProductCategoriesViewModel(

    val getCategoriesUseCase: GeListCategoriesUseCase

): NetworkViewModel() {

    var state by mutableStateOf(ProductCategoriesState())

    fun processIntents( intent: ProductCategoriesIntents ) {

        when ( intent ) {

            is ProductCategoriesIntents.SetScreen -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    if ( state.isSet ) {

                        val listCategories = getCategoriesUseCase.execute()

                        state = state.copy(

                            listCategories = listCategories,

                            isSet = false

                        )

                    }

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ProductCategoriesIntents.SelectCategory -> selectCategory()

            is ProductCategoriesIntents.Back -> back()

        }

    }

    fun selectCategory(){

        val catalogProducts: CatalogProductsScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(catalogProducts.catalogProductsScreen())

    }

    fun back(){

        val profile: ProfileScreensApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(profile.profile())

    }
}