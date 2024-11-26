package file_provider

import org.koin.core.module.Module
import org.koin.dsl.module

actual val fileProviderModule: Module
    get() = module {
        single {
            AndroidVideoPicker(get()) as FileProviderApi
        }
    }