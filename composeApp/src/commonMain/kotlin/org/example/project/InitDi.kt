package org.example.project

import com.catalogProductsModule
import com.categoriesModule
import com.example.notes_screens_impl.notesModule
import com.locationsModule
import com.productCategoriesModule
import com.productsMenuModule
import com.profile.`printer-impl`.profileScreensModule
import com.profile.profile.toolsScreensModule
import com.profile.profile.udpPlayer.printerPlatformModule
import com.profile.profile.udpPlayer.printerScreenModule
import com.project.chats.core.chatsModule
import com.project.core_app.components.menu_bottom_bar.menuBottomBarMudule
import org.example.project.app.moduls_di.appModule
import com.project.`local-storage`.`profile-storage`.localStorageModule
import com.project.organizationScreenModule
import com.project.menu_crm_api.menuCrmModule
import com.project.network.common.networkModule
import com.project.`outhorization-screen-impl`.authorizationModule
import com.project.project_conterol.projectControlModule
import com.project.tape.tapeScreenModule
import com.specificationsModule
import com.warehouseScreensModule
import contactProviderModule
import contragentsModule
import crmModule
import file_provider.fileProviderModule
import goodsAndServicesModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import unitsMeasurementModule

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            chatsModule,
            organizationScreenModule,
            menuCrmModule,
            tapeScreenModule,
            profileScreensModule,
            localStorageModule,
            platforModule,
            appModule,
            authorizationModule,
            notesModule,
            networkModule,
            printerPlatformModule,
            printerScreenModule,
            warehouseScreensModule,
            contactProviderModule,
            contragentsModule,
            crmModule,
            projectControlModule,
            specificationsModule,
            locationsModule,
            menuBottomBarMudule,
            toolsScreensModule,
            productsMenuModule,
            categoriesModule,
            unitsMeasurementModule,
            fileProviderModule,
            //scannerZebraUsbModule,
            goodsAndServicesModule,
            productCategoriesModule,
            catalogProductsModule

        )
    }
}

