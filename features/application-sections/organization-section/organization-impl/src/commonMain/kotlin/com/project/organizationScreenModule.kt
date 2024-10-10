package com.project

import com.project.menu.screen.OrganizationScreenApi
import org.koin.dsl.module

val organizationScreenModule = module {
    factory {
        OrganizationScreenImpl() as OrganizationScreenApi
    }

}