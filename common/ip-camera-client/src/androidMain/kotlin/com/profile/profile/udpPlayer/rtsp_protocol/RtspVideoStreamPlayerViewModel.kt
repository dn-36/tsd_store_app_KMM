package com.profile.profile.udpPlayer.rtsp_protocol

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import java.io.File
 class RtspVideoStreamPlayerViewModel {

    private var context: Context? = null
    private var libVLC: LibVLC? = null
     var mediaPlayer: MediaPlayer? = null
    private var isRecording = false
    private var outputFile: File? = null
    val isAttached  = mutableStateOf(false)
    val isSecuessConect = mutableStateOf(false)
    var media:Media? = null
    var urlStream:String = ""

    fun initialize(context: Context) {
        this.context = context
        libVLC = LibVLC(context, arrayListOf("--file-caching=2000", "--rtsp-tcp"))
        mediaPlayer = MediaPlayer(libVLC)
    }

    suspend fun startStream(rtspUrl: String) {
        this.urlStream = urlStream
        if (mediaPlayer == null || context == null) {
            Log.e("RTSP", "Player is not initialized")
            return
        }
        withContext(Dispatchers.IO) {
            try {
                 media = Media(libVLC, Uri.parse(rtspUrl)).apply {
                    setHWDecoderEnabled(true, false)
                    addOption(":network-caching=150")
                    addOption(":clock-jitter=0")
                    addOption(":clock-synchro=0")

                }
                mediaPlayer?.media = media
                mediaPlayer?.play()
                Log.d("RTSP", "Stream started: $rtspUrl")
                mediaPlayer!!.setEventListener { event ->
                    when (event.type) {
                        MediaPlayer.Event.Playing -> {
                            isSecuessConect.value = true
                            Log.d("RTSP", "Stream successfully started!")
                        }

                        MediaPlayer.Event.Buffering -> {
                            Log.d("RTSP", "Stream buffering: ${event.buffering}")
                        }

                        MediaPlayer.Event.Stopped -> {
                            Log.d("RTSP", "Stream stopped.")
                        }

                        MediaPlayer.Event.EndReached -> {
                            Log.d("RTSP", "Stream ended.")
                        }

                    }
                }
              //  isSecuessConect.value = true
            } catch (e: Exception) {
                Log.e("RTSP", "Failed to start stream: ${e.localizedMessage}")
            }
        }
    }

    fun stopStream() {
        try {
            mediaPlayer?.stop()
            Log.d("RTSP", "Stream stopped")
        } catch (e: Exception) {
            Log.e("RTSP", "Failed to stop stream: ${e.localizedMessage}")
        }
    }

    suspend fun startRecording(rtspUrl: String) {
        if (!isSecuessConect.value) {
            Log.w("RTSP", "Recording is already in progress")
            Toast.makeText(context,"Запись невозможна так как нет содинения", Toast.LENGTH_SHORT).show()
            return
        }



        withContext(Dispatchers.IO) {


            try {
                //outputFile = File(context!!.getExternalFilesDir(null), "recorded_stream.mp4")
                outputFile = File(context!!.getExternalFilesDir(null), "recorded_stream${(0..100_000).random()}.mp4")

                val media = Media(libVLC, Uri.parse(rtspUrl)).apply {
                    setHWDecoderEnabled(true, false)
                    addOption(":network-caching=150")
                    addOption(":clock-jitter=0")
                    addOption(":clock-synchro=0")
                    addOption(":sout=#duplicate{dst=display,dst=file{dst=${outputFile!!.absolutePath}}}")

                }
                mediaPlayer?.media = media
                mediaPlayer?.play()
                isRecording = true
                Log.d("RTSP", "Recording started: ${outputFile!!.absolutePath}")
            } catch (e: Exception) {
                Log.e("RTSP", "Failed to start recording: ${e.localizedMessage}")
            }
        }
    }

    suspend fun stopRecording() {


            Log.w("RTSP", "Recording is not in progress")
        withContext(Dispatchers.IO) {
            try {
                mediaPlayer?.stop()
                startStream(rtspUrl = "rtsp://192.168.1.150:2000/unicast")
                Log.d("RTSP", "Recording stopped")
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context, "Видео сохранено в папке: " +
                                outputFile!!.absolutePath.toString(), Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Log.e("RTSP", "Failed to stop recording: ${e.localizedMessage}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context, "Видео не сохранено", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    }

