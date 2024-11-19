package com.project.core_app.utils

fun boxHeight (listSize: Int ) : Int {

    val newSize = 60 * listSize

    return if ( newSize <= 200 ) newSize else 200

}