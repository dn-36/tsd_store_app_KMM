package com.project.tape

import cafe.adriel.voyager.core.screen.Screen
import com.project.menu.screen.TapeScreenApi

class TapeScreenImpl : TapeScreenApi{

    override fun tape(): Screen {
         return TapeScreen()
    }


}