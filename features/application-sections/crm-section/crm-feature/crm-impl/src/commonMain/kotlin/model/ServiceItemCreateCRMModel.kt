package model

data class ServiceItemCreateCRMModel(
    val number: Int?,
    val type_id: Int,  // Обязательный id поля из items
    val name: String?, // Значение в зависимости от типа
    val file: Int? = null,      // Поле для файлов, отправляется как 0 для типов file, stl, obj, fbx
    val filename: Int? = null,  // Имя файла, также 0 для типов file, stl, obj, fbx
    val req: Int,      // 1 для обязательного, 0 для необязательного
    val type: String?  // Тип данных, например, text, alltext, man, specification и т.д.

)
