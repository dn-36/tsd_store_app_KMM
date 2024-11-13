package com.project.core_app.components.menu_bottom_bar

import com.project.core_app.components.menu_bottom_bar.datasource.MenuBottomBarRepositoryImpl
import com.project.core_app.components.menu_bottom_bar.domian.GetCountNewMessageUseCase
import com.project.core_app.components.menu_bottom_bar.domian.MenuBottomBarRepositoryApi
import com.project.core_app.components.menu_bottom_bar.viewmodel.MenuBottomBarViewModel
import org.koin.dsl.module

val menuBottomBarMudule = module {
    factory {
        MenuBottomBarViewModel(get())
    }
    factory {
        MenuBottomBarRepositoryImpl(get(),get()) as MenuBottomBarRepositoryApi
    }
    factory {
        GetCountNewMessageUseCase(get())
    }
}