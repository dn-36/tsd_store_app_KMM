package com

import com.datasource.CategoryClientImpl
import com.domain.repository.CategoryClientApi
import com.domain.usecases.GetCategoriesUseCase
import com.viewmodel.CategoriesViewModel
import com.domain.usecases.CreateCategoryUseCase
import com.domain.usecases.DeleteCategoryUseCase
import com.domain.usecases.UpdateCategoryUseCase
import com.project.network.category_network.CategoryClient
import org.koin.dsl.module

val categoriesModule = module {

    factory { CategoriesScreenImpl() as CategoriesScreenApi }


    factory { CategoryClientImpl(get(), get()) as CategoryClientApi }

    factory { CategoryClientImpl(get(), get()) as CategoryClientApi }

    factory { GetCategoriesUseCase(get()) }

    factory { CreateCategoryUseCase(get()) }

    factory { UpdateCategoryUseCase(get()) }

    factory { DeleteCategoryUseCase(get()) }

    factory { CategoriesViewModel(get(), get(), get(), get()) }

    factory { CategoryClient() }

}