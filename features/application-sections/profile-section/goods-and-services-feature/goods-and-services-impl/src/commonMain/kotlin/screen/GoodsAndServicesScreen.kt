package screen

import com.project.core_app.network_base_screen.NetworkScreen
import com.project.network.notes_network.model.NoteResponse
import component.GoodsAndServicesComponent
import org.koin.mp.KoinPlatform.getKoin

class GoodsAndServicesScreen( private val sku: String ): NetworkScreen (

    GoodsAndServicesComponent( sku, getKoin().get() )

)