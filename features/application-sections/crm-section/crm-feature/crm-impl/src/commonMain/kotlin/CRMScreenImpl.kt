import cafe.adriel.voyager.core.screen.Screen
import screen.CRMScreen

class CRMScreenImpl : CRMScreenApi {

    override fun crm(): Screen {

        return CRMScreen()

    }

}