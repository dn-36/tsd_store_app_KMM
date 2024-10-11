import androidx.core.app.ComponentActivity
import contact_provider.AndroidContactProvider
import contact_provider.ContactProviderApi
import org.koin.core.module.Module
import org.koin.dsl.module

actual val contactProviderModule: Module
    get() = module {
        single {
            AndroidContactProvider(get()) as ContactProviderApi
        }
    }
