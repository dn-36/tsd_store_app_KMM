package com.project.tape

import com.project.menu.screen.TapeScreenApi
import org.koin.dsl.module


val tapeScreenModule = module {
    factory {
        TapeScreenImpl() as TapeScreenApi
    }

}