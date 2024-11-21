package com.project.menu_crm_api

import com.project.`menu-crm-api`.MenuCrmScreenApi
import org.koin.dsl.module

val menuCrmModule = module {

    factory { MenuCrmScreenImpl() as MenuCrmScreenApi }

}