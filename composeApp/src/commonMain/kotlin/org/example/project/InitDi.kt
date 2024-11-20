package org.example.project

import com.example.notes_screens_impl.notesModule
import com.locationsModule
import com.profile.`printer-impl`.profileScreensModule
import com.profile.profile.toolsScreensModule
import com.profile.profile.udpPlayer.printerPlatformModule
import com.profile.profile.udpPlayer.printerScreenModule
import com.profile.profile.warehouseScreensModule
import com.project.chats.core.chatsModule
import com.project.core_app.components.menu_bottom_bar.menuBottomBarMudule
import org.example.project.app.moduls_di.appModule
import com.project.`local-storage`.`profile-storage`.localStorageModule
import com.project.organizationScreenModule
import com.project.`menu-crm-impl`.menuCrmModule
import com.project.network.common.networkModule
import com.project.`outhorization-screen-impl`.authorizationModule
import com.project.project_conterol.projectControlModule
import com.project.tape.tapeScreenModule
import com.specificationsModule
import contactProviderModule
import contragentsModule
import crmModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

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
            toolsScreensModule

        )
    }
}

