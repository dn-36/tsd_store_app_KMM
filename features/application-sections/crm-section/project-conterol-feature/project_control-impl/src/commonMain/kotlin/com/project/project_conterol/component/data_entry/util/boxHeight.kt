package com.project.project_conterol.component.data_entry.util

fun boxHeight (listSize: Int ) : Int {

    val newSize = 60 * listSize

    return if ( newSize <= 200 ) newSize else 200

}