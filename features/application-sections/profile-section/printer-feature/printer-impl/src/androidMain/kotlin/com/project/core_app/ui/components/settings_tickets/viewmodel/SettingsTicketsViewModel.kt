package com.project.core_app.ui.components.settings_tickets.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.positionChange

class SettingsTicketsViewModel(){
    var state by mutableStateOf(SettingsTicketsState())
    //    private set

    fun processIntent(intent:SettingsTicketsIntent){
       when(intent){
         is  SettingsTicketsIntent.SetCoordinatesQRcode -> {
             setCoordinatesQRcode(
                 intent.initX,
                 intent.initY,
                 intent.height,
                 intent.widht,
                 intent.barcode,
                 intent.text,
                 intent.heightQrCode,
                 intent.actioTscPrinter
             )
         }

           is SettingsTicketsIntent.ChangeCoordinatesQRcode -> {
               changeCoordinatesQRcode(intent.x,intent.y)
           }
       }
    }
    private fun setCoordinatesQRcode(
        initX:Int,
        initY:Int,
        height:Int,
        widht: Int,
        barcode: Bitmap,
        text: Bitmap,
        heightQrCode:Int,
        actioTscPrinter: (x: Int, y: Int, height: Int, widht: Int) -> Unit
        ){
        state = state
            .copy(x = initX,y = initY, heightTicketMM = height, widthTicketMM = widht)

        if (state.widthTicketMM.toString().isNotBlank() &&
            state.heightTicketMM.toString().isNotBlank()) {
            state = state
                .copy (x = ((state.widthTicketMM.toFloat() * 2.25F) -
                        ((barcode.width / 4))).toInt(),
                    y = (((state.heightTicketMM .toFloat() * 2.25F) -
                            (heightQrCode + (text.height / 4))) + 30).toInt()
                )
                Log.d("qwert",heightQrCode.toString())

            actioTscPrinter(
                (state.x * 1.5).toInt(),
                (state.y * 1.5).toInt(),
                state.heightTicketMM,
                state.widthTicketMM
            )

        } else {
            state = state.copy(widthTicketMM = 10)
            state = state.copy(heightTicketMM = 10)
        }


        /*
         state = state
             .copy(x = initX,y = initY, heightTicketMM = height, widthTicketMM = widht)

        if (state.widthTicketMM.toString().isNotBlank()) {
            state = state
                .copy (x = ((state.widthTicketMM.toFloat() * 2.25F) -
                        ((barcode.width / 4))).toInt())


            actioTscPrinter(
                (state.x * 1.5).toInt(),
                (state.y * 1.5).toInt(),
                state.heightTicketMM,
                state.widthTicketMM
            )

        } else {
            state = state.copy(widthTicketMM = 10)
        }

        if (state.heightTicketMM.toString().isNotBlank()) {
            state = state.copy(y =
                (((state.heightTicketMM .toFloat() * 2.25F) -
                        (barcode.height / 4 + (text.height / 4))) + 30).toInt()
            )
            state = state.copy(heightTicketMM = state.heightTicketMM)
            // heightTicketMM = it.toInt()
            actioTscPrinter(
                (state.x.toInt() * 1.5).toInt(),
                (state.y.toInt() * 1.5).toInt(),
                state.heightTicketMM,
                state.widthTicketMM
            )
        } else {
            state = state.copy(heightTicketMM = 10)
           // heightTicketMM = 10
        }

*/
    }
    fun changeCoordinatesQRcode(x:Int,y:Int){
        state =
            state.copy(
                x = (state.x + (x / 2)),
                y = (state.y + (y / 2))
            )
    }
}


sealed class SettingsTicketsIntent(){
    data class SetCoordinatesQRcode(
        val initX:Int = 42,
        val initY:Int = 90,
        val barcode:Bitmap,
        val text: Bitmap,
        val height:Int,
        val widht: Int,
        val heightQrCode:Int,
        val actioTscPrinter: (x: Int, y: Int, height: Int, widht: Int) -> Unit
    ) : SettingsTicketsIntent()

    data class ChangeCoordinatesQRcode(val x:Int,val y:Int)
        : SettingsTicketsIntent()
}


