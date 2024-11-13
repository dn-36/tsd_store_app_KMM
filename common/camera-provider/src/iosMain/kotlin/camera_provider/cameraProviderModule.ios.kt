package camera_provider

import org.koin.core.module.Module
import org.koin.dsl.module

actual val cameraProviderModule: Module
    get() = module {
        single {
            IOSPermissionHandler() as CameraProviderApi
        }
    }