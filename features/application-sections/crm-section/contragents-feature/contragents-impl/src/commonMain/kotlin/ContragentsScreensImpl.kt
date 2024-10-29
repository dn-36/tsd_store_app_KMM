import cafe.adriel.voyager.core.screen.Screen
import screens.ContragentsScreen

class ContragentsScreensImpl: ContragentsScreensApi {

    override fun contragents(): Screen {

        return ContragentsScreen()

    }
}