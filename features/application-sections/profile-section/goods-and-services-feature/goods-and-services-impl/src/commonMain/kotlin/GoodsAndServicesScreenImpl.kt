import cafe.adriel.voyager.core.screen.Screen
import screens.GoodsAndServicesScreen

class GoodsAndServicesScreenImpl: GoodsAndServicesScreenApi {

    override fun goodsAndServicesScreen(): Screen {

        return GoodsAndServicesScreen()

    }

}