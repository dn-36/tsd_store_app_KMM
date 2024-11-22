package datasource

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.units_measurement_network.UnitsMeasurementClient
import domain.repository.UnitsMeasurementClientApi
import model.UnitResponseModel

class UnitsMeasurementClientImpl (

    val unitsMeasurementClient: UnitsMeasurementClient,

    val sharedPrefsApi: SharedPrefsApi


): UnitsMeasurementClientApi {

    override suspend fun getToken(): String {

        return sharedPrefsApi.getToken()?:""

    }

    override suspend fun getUnitsMeasurement(): List<UnitResponseModel> {

        unitsMeasurementClient.init(getToken())

        return unitsMeasurementClient.getUnits().map {

            UnitResponseModel(

                id = it.id,
                name = it.name,
                ui = it.ui,
                created_at = it.created_at,
                updated_at = it.updated_at

            )

        }

    }

    override suspend fun createUnitMeasurement(name: String) {

        unitsMeasurementClient.init(getToken())

        unitsMeasurementClient.createUnits(name)

    }

    override suspend fun deleteUnitMeasurement(ui: String) {

        unitsMeasurementClient.init(getToken())

        unitsMeasurementClient.deleteUnit(ui)

    }

    override suspend fun updateUnitMeasurement(name: String, ui: String) {

        unitsMeasurementClient.init(getToken())

        unitsMeasurementClient.updateUnits(name, ui)

    }

}