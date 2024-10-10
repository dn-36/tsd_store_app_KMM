package com.profile.profile

import com.project.chats.WarehouseScreensApi
import org.koin.dsl.module

val warehouseScreensModule = module {
   factory {
    WarehouseScreensImpl() as WarehouseScreensApi
   }
}