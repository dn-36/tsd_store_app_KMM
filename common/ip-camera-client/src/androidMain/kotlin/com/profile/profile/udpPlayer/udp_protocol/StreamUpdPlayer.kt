package com.profile.profile.udpPlayer.udp_protocol

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.MulticastSocket


actual class StreamUdpPlayer actual constructor(
    val multicastAddress: String,
    val multicastPort: Int,
    val packetSize: Int
) {
    private var udpReceiver: UdpMulticastReceiver? = null
    val bitmap = mutableStateOf<Bitmap?>(null)

    actual fun startVideoStream(scope: CoroutineScope) {

        udpReceiver = UdpMulticastReceiver(
            address = multicastAddress,
            port = multicastPort,
            packetSize = packetSize
        ) { frame ->
            scope.launch {
                processFrame(frame)
            }
        }
        udpReceiver?.startReceiving(scope)
    }

    private suspend fun processFrame(frame: ByteArray) {
        decodeMjpegFrame(frame)
    }

    suspend fun decodeMjpegFrame(data: ByteArray) {
        withContext(Dispatchers.IO) {
            try {

                val boundary = "--BoundaryString".toByteArray()
                val frames = splitByteArray(data, boundary)

                for (frame in frames) {
                    if (frame.isNotEmpty()) {
                        // Поиск начала JPEG-данных
                        val startIndex = frame.indexOf(0xFF.toByte()) // Начало JPEG
                        val jpegData = frame.copyOfRange(startIndex, frame.size)
                        if (startIndex >= 0 ) {



                            val inputStream = ByteArrayInputStream(jpegData)
                            val decodedBitmap = BitmapFactory.decodeStream(inputStream)

                            if (decodedBitmap != null) {
                                withContext(Dispatchers.Main) {
                                    bitmap.value = decodedBitmap
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("MJPEG Decoder", "Error decoding MJPEG frame", e)
            }
        }
    }

    // Функция для разделения ByteArray по границе
    fun splitByteArray(data: ByteArray, boundary: ByteArray): List<ByteArray> {
        val frames = mutableListOf<ByteArray>()
        var start = 0
        var end: Int

        while (start < data.size) {
            end = indexOf(data, boundary, start)
            if (end == -1) {
                frames.add(data.copyOfRange(start, data.size))
                break
            }
            frames.add(data.copyOfRange(start, end))
            start = end + boundary.size
        }

        return frames
    }

    // Функция для поиска индекса первого вхождения границы в ByteArray
    fun indexOf(data: ByteArray, target: ByteArray, start: Int = 0): Int {
        outer@ for (i in start..data.size - target.size) {
            for (j in target.indices) {
                if (data[i + j] != target[j]) continue@outer
            }
            return i
        }
        return -1
    }
    private class UdpMulticastReceiver(
        private val address: String,
        private val port: Int,
        private val packetSize: Int,
        private val onFrameReceived: (ByteArray) -> Unit
    ) {
        private var socket: MulticastSocket? = null
        private var isReceiving = false


        private var frameBuffer = ByteArray(0)

        fun startReceiving(scope: CoroutineScope) {
            isReceiving = true

            scope.launch(Dispatchers.IO) {
                try {
                    val group = InetAddress.getByName(address)
                    socket = MulticastSocket(port).apply {
                        joinGroup(group)
                    }

                    val buffer = ByteArray(packetSize)

                    while (isReceiving) {
                        try {
                            val packet = DatagramPacket(buffer, buffer.size)

                            socket?.receive(packet)

                            val receivedData = packet.data.copyOf(packet.length)
                            frameBuffer += receivedData

                            val endIndex = frameBuffer.indexOfSequence(byteArrayOf(0xFF.toByte(), 0xD9.toByte()))

                            if (endIndex != -1) {
                                // Извлекаем полный кадр
                                val fullFrame = frameBuffer.copyOfRange(0, endIndex + 2)

                                // Передаем кадр в обработчик
                                withContext(Dispatchers.IO) {
                                    onFrameReceived(fullFrame)
                                }

                                // Удаляем обработанные данные из буфера
                                frameBuffer = frameBuffer.copyOfRange(endIndex + 2, frameBuffer.size)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    stopReceiving()
                }
            }
        }

        fun ByteArray.indexOfSequence(sequence: ByteArray, start: Int = 0): Int {
            if (sequence.isEmpty() || this.size < sequence.size || start < 0) return -1
            for (i in start..this.size - sequence.size) {
                if (this.copyOfRange(i, i + sequence.size).contentEquals(sequence)) {
                    return i
                }
            }
            return -1
        }



        fun stopReceiving() {
            isReceiving = false
            try {
                socket?.leaveGroup(InetAddress.getByName(address))
                socket?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    @Composable
    actual fun VideoStream(modifier: Modifier) {
        if (bitmap.value != null) {

            Image(
                bitmap = bitmap.value!!.asImageBitmap(),
                contentDescription = null,
                modifier = modifier
            )
        }else{
         Box(modifier = Modifier.fillMaxSize()) {
             Column(modifier = Modifier.align(Alignment.Center)) {
                 CircularProgressIndicator(modifier = Modifier.size(35.dp).align(Alignment.CenterHorizontally))
                 Text(
                     "соединение...",
                     modifier = Modifier.align(Alignment.CenterHorizontally),
                     fontSize = 30.sp
                     )
             }
         }
        }
    }

    actual fun stopVideoStream() {
       udpReceiver?.stopReceiving()
    }
}

