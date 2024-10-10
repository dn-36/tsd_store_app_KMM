package org.example.project

import com.profile.`printer-impl`.profileScreensModule
import com.profile.profile.qr_code.printerPlatformModule
import com.profile.profile.qr_code.printerScreenModule
import com.profile.profile.warehouseScreensModule
import com.project.chats.chatsModule
import org.example.project.app.moduls_di.appModule
import com.project.`local-storage`.`profile-storage`.localStorageModule
import com.project.organizationScreenModule
import com.project.`menu-crm-impl`.menuCrmModule
import com.project.network.network_module
import com.project.`outhorization-screen-impl`.authorizationModule
import com.project.tape.tapeScreenModule
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
            network_module,
            printerPlatformModule,
            printerScreenModule,
            warehouseScreensModule
        )
    }
}

