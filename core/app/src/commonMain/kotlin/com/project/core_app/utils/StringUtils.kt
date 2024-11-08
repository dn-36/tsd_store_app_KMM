package com.project.core_app.utils

fun String.truncateString( maxLength: Int): String {
    if (this.length <= maxLength) return this
    return this.take(maxLength) + "..."
}