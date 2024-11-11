import org.koin.core.module.Module
import org.koin.dsl.module

actual val datePickerModule: Module
    get() = module {
        single {
           DatePickerIos(get()) as DatePickerApi
        }
    }