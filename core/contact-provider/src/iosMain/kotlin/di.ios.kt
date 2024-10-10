import contact_provider.ContactProviderApi
import contact_provider.IOSContactProvider
import org.koin.core.module.Module
import org.koin.dsl.module

actual val coreModuleContactProvider: Module
    get() = module {
        single {
            IOSContactProvider() as ContactProviderApi
        }
    }