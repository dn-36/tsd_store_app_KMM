import contact_provider.ContactProviderApi
import contact_provider.IOSContactProvider
import org.koin.core.module.Module
import org.koin.dsl.module

actual val contactProviderModule: Module
    get() = module {
        single {
            IOSContactProvider() as ContactProviderApi
        }
    }