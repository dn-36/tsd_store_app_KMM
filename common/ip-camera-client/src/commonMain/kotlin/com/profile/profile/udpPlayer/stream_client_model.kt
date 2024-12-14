package com.profile.profile.udpPlayer

import com.profile.profile.udpPlayer.rtsp_protocol.RtspVideoStreamPlayerComponent
import org.koin.dsl.module

val  stream_client_model = module {
    single {
        RtspVideoStreamPlayerComponent()
    }

}
