package camera_provider

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.requestAccessForMediaType
import kotlin.coroutines.resume
import platform.AVFoundation.*

class IOSPermissionHandler : CameraProviderApi {

     override suspend fun requestCameraPermission(): Boolean {

        return suspendCancellableCoroutine { continuation ->

            when (AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)) {
                AVAuthorizationStatusAuthorized -> {
                    // Разрешение уже предоставлено
                    continuation.resume(true)
                }
                AVAuthorizationStatusNotDetermined -> {
                    // Разрешение еще не запрашивалось, запрашиваем
                    AVCaptureDevice.requestAccessForMediaType(AVMediaTypeVideo) { granted ->
                        continuation.resume(granted)
                    }
                }
                else -> {

                    continuation.resume(false)
                }
            }
        }
    }
}