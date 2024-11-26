package file_provider

interface FileProviderApi {

    suspend fun pickVideo(): String?

}