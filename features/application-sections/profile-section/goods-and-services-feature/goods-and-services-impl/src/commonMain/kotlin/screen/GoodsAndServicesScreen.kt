package screen

import com.project.core_app.network_base_screen.NetworkScreen
import component.GoodsAndServicesComponent
import org.koin.mp.KoinPlatform.getKoin

class GoodsAndServicesScreen: NetworkScreen (

    GoodsAndServicesComponent(getKoin().get())

)