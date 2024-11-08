
import com.project.core_app.network_base_screen.NetworkScreen
import component.CRMComponent
import org.koin.mp.KoinPlatform.getKoin



class CRMScreen :NetworkScreen (

    CRMComponent(getKoin().get())

)