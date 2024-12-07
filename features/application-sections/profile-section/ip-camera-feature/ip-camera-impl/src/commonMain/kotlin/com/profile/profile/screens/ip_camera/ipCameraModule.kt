package com.profile.profile.screens.ip_camera

import com.profile.profile.screens.ip_camera.domain.InfrastructureIpCameraApi
import com.profile.profile.screens.ip_camera.domain.StartRecordStreamUseCase
import com.profile.profile.screens.ip_camera.domain.StartStreamtUseCase
import com.profile.profile.screens.ip_camera.domain.StopRecordStreamUseCase
import com.profile.profile.screens.ip_camera.domain.StopStreamUseCase
import com.profile.profile.screens.ip_camera.domain.StreamViewConentUseCase
import com.profile.profile.screens.ip_camera.repository.InfrastructureIpCameraImpl
import com.profile.profile.screens.ip_camera.viewmodel.IpCameraViewModel
import com.profile.profile.udpPlayer.rtsp_protocol.RtspVideoStreamPlayerComponent
import org.koin.dsl.module

val  ipCameraModule = module {
    factory {
        InfrastructureIpCameraImpl(get()) as InfrastructureIpCameraApi
    }
    factory {
        IpCameraScreen()
    }
    factory {
        StreamViewConentUseCase(get())
    }
    factory {
        IpCameraViewModel()
    }
    factory {
        RtspVideoStreamPlayerComponent()
    }
    factory {
        StopStreamUseCase(get())
    }
    factory {
        StopRecordStreamUseCase(get())
    }
    factory {
        StartStreamtUseCase(get())
    }
    factory {
        StartRecordStreamUseCase(get())
    }
}