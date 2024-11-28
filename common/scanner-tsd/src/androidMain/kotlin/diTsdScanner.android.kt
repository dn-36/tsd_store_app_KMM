import org.koin.core.module.Module
import org.koin.dsl.module

actual val tsdScannerModule: Module
    get() = module {
        single {
            TsdScannerManager(get()) as TsdScannerApi
        }
    }