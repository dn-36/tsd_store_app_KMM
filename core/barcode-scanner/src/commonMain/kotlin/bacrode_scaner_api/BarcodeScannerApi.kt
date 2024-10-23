package bacrode_scaner_api

interface BarcodeScannerApi {

    suspend fun scan(): String?

}