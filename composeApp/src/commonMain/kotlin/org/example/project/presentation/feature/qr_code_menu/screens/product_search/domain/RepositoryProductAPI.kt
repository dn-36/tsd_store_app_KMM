package org.example.project.presentation.feature.qr_code_menu.screens.product_search.domain

interface RepositoryProductAPI {

    suspend fun getProduct():List<String>

}