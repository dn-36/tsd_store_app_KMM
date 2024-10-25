package camera_provider

interface CameraProviderApi {

    suspend fun requestCameraPermission(): Boolean

}