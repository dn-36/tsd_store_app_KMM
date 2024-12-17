import org.koin.core.module.Module
import org.koin.dsl.module

actual val usbScannerModule: Module
    get() = module {
        single {
            UsbScannerAndroid(get()) as UsbScannerApi
        }
    }