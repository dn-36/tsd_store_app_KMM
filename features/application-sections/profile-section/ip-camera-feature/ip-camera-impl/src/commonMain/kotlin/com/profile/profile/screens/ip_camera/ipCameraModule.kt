package com.profile.profile.screens.ip_camera

import com.profile.profile.screens.ip_camera.domain.InfrastructureIpCameraApi
import com.profile.profile.screens.ip_camera.domain.IpCameraUseCase
import com.profile.profile.screens.ip_camera.repository.InfrastructureIpCameraImpl
import com.profile.profile.screens.ip_camera.viewmodel.IpCameraViewModel
import com.profile.profile.udpPlayer.rtsp_protocol.RtspVideoStreamPlayer
import org.koin.dsl.module

val  ipCameraModule = module {
    factory {
        InfrastructureIpCameraImpl(get()) as InfrastructureIpCameraApi
    }
    factory {
        IpCameraScreen()
    }
    factory {
        IpCameraUseCase(get())
    }
    factory {
        IpCameraViewModel(get())
    }
    factory {
        RtspVideoStreamPlayer()
    }
}