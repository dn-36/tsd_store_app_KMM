import cafe.adriel.voyager.core.screen.Screen
import screen.ContragentsScreen

class ContragentsScreensImpl: ContragentsScreensApi {

    override fun contragents(): Screen {

        return ContragentsScreen()

    }
}