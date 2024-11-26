package file_provider

class IOSVideoPicker : FileProviderApi {
    override suspend fun pickVideo(): String? {
        // Реализация для iOS (зависит от использования UIKit или SwiftUI)
        println("Выбор видео для iOS не реализован.")
        return null
    }
}