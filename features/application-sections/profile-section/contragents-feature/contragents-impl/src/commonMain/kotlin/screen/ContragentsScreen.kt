package screen


import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin
import screen.component.ContragentsComponent


class ContragentsScreen : NetworkScreen (

    ContragentsComponent( getKoin().get() )

)