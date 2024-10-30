import cafe.adriel.voyager.core.screen.Screen

class CRMScreenImpl : CRMScreenApi {

    override fun crm(): Screen {

        return CRMScreen()

    }

}