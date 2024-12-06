import cafe.adriel.voyager.core.screen.Screen
import screen.GoodsAndServicesScreen

class GoodsAndServicesScreenImpl: GoodsAndServicesScreenApi {

    override fun goodsAndServicesScreen(): Screen {

        return GoodsAndServicesScreen()

    }

}