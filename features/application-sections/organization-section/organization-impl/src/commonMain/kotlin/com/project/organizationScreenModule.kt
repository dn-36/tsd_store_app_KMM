package com.project

import com.project.datasource.OrganizationClientImpl
import com.project.domain.usecases.CreateOrganizationUseCase
import com.project.domain.repository.OrganizationClientApi
import com.project.domain.usecases.ChoosingActiveOrganizationUseCase
import com.project.domain.usecases.DeleteOrganizationUseCase
import com.project.domain.usecases.GetOrganizationUseCase
import com.project.menu.screen.OrganizationScreenApi
import com.project.network.organizations_network.OrganizationsClient
import com.project.viewmodel.OrganizationsViewModel
import org.koin.dsl.module

    val organizationScreenModule = module {

    factory { OrganizationScreenImpl() as OrganizationScreenApi }

    factory { OrganizationsClient() }

    factory { OrganizationClientImpl(get()) as OrganizationClientApi }

    factory { OrganizationsViewModel(get(),get(),get(),get()) }

    factory { CreateOrganizationUseCase(get()) }

    factory { DeleteOrganizationUseCase(get()) }

    factory { ChoosingActiveOrganizationUseCase(get()) }

    factory { GetOrganizationUseCase(get()) }

    }