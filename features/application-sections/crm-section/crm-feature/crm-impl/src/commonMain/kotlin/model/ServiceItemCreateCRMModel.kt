package model

data class ServiceItemCreateCRMModel(

    val type_id: Int,  // Обязательный id поля из items
    val name: String?, // Значение в зависимости от типа

)
