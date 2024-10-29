package screens


import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin
import screens.component.ContragentsComponent


class ContragentsScreen : NetworkScreen (

    ContragentsComponent( getKoin().get() )

)