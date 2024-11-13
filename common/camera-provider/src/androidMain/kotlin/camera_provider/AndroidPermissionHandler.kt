package camera_provider

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AndroidPermissionHandler( val context: Context): CameraProviderApi {

      fun request(): Boolean {

        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    override suspend fun requestCameraPermission():Boolean {

        if (!request()) {

            request()
        }

        return true
    }

}