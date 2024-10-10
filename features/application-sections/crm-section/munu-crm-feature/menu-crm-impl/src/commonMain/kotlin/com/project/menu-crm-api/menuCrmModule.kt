package com.project.`menu-crm-impl`

import com.project.`menu-crm-api`.MenuCrmScreenApi
import org.koin.dsl.module

val menuCrmModule = module {
    factory {
        MenuCrmScreenImpl() as MenuCrmScreenApi
    }
}