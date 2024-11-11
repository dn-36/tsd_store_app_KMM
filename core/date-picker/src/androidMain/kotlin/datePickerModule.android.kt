import org.koin.core.module.Module
import org.koin.dsl.module

actual val datePickerModule: Module
    get() = module {
        single {
            DatePickerAndroid() as DatePickerApi
        }
    }