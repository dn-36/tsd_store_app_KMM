import cafe.adriel.voyager.core.screen.Screen
import screen.UnitsMeasurementScreen

class UnitsMeasurementScreenImpl: UnitsMeasurementScreenApi {

    override fun unitMeasurement(): Screen {

        return UnitsMeasurementScreen()

    }

}