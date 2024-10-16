package com.project

import com.project.datasource.OrganizationClientImpl
import com.project.domain.usecases.CreateOrganizationUseCase
import com.project.domain.repository.OrganizationClientApi
import com.project.menu.screen.OrganizationScreenApi
import com.project.viewmodel.OrganizationsViewModel
import org.koin.dsl.module

val organizationScreenModule = module {

    factory {

        OrganizationScreenImpl() as OrganizationScreenApi

    }

    factory { OrganizationClientImpl(get()) as OrganizationClientApi }

    factory { OrganizationsViewModel(get(),get()) }

    factory { CreateOrganizationUseCase(get()) }

}