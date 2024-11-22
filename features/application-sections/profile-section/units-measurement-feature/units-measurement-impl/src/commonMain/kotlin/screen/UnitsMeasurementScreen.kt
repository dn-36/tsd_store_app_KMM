package screen

import com.project.core_app.network_base_screen.NetworkScreen
import component.UnitsMeasurementComponent
import org.koin.mp.KoinPlatform.getKoin

class UnitsMeasurementScreen: NetworkScreen (

    UnitsMeasurementComponent(getKoin().get())

)