package org.example.project

import com.example.notes_screens_impl.notesModule
import com.profile.`printer-impl`.profileScreensModule
import com.profile.profile.qr_code.printerPlatformModule
import com.profile.profile.qr_code.printerScreenModule
import com.profile.profile.warehouseScreensModule
import com.project.chats.core.chatsModule
import org.example.project.app.moduls_di.appModule
import com.project.`local-storage`.`profile-storage`.localStorageModule
import com.project.organizationScreenModule
import com.project.`menu-crm-impl`.menuCrmModule
import com.project.network.networkModule
import com.project.`outhorization-screen-impl`.authorizationModule
import com.project.project_conterol.projectControlModule
import com.project.tape.tapeScreenModule
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
            projectControlModule

        )
    }
}

