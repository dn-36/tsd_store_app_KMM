package file_provider

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AndroidVideoPicker(private val context: Context) : FileProviderApi {

    override suspend fun pickVideo(): String? {
        // Проверяем, предоставлено ли разрешение
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_EXTERNAL_STORAGE
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            // Открываем диалог выбора видео и возвращаем результат
            return openVideoPicker()?.toString()
        } else {
            // Разрешение не предоставлено
            println("Нет разрешения на доступ к внешнему хранилищу.")
            return null
        }
    }

    private suspend fun openVideoPicker(): Uri? {
        return suspendCancellableCoroutine { continuation ->
            val activity = context as AppCompatActivity
            val launcher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val selectedVideoUri = result.data?.data
                    continuation.resume(selectedVideoUri)
                } else {
                    continuation.resume(null)
                }
            }

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "video/*" // Фильтр только для видеофайлов
            }
            launcher.launch(intent)
        }
    }
}