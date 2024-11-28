package file_provider

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import kotlinx.coroutines.suspendCancellableCoroutine

class AndroidVideoPicker(private val context: Context) : FileProviderApi {

    override suspend fun pickVideo(): String? {
        // Проверяем разрешения
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_EXTERNAL_STORAGE
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED ||

            ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_MEDIA_VIDEO
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            // Открываем диалог выбора видео

            return  openVideoPicker()// Возврат URI делегируется вызывающему коду через интент
        } else {
            // Сообщаем об отсутствии разрешения
            return null
        }
    }

    private suspend fun openVideoPicker(): String? {
        return suspendCancellableCoroutine { continuation ->
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "video/*" // Фильтр только для видеофайлов
            }

            // Запускаем Intent и ожидаем результат
            val chooser = Intent.createChooser(intent, "Выберите видео").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(chooser)

        }
    }
}