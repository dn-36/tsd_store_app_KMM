package component.data_entry.util

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

fun boxGHeight ( listSize: Int ) : Int {

    val newSize = 60 * listSize

    return if ( newSize <= 200 ) newSize else 200

}