package org.example.project

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.project.phone.PermissionManeger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.app.ui.App
import org.koin.android.ext.koin.androidContext
import java.io.ByteArrayInputStream
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.MulticastSocket

class MainActivity : ComponentActivity() {

    private lateinit var udpReceiver: UdpMulticastReceiver
    val multicastAddress = "236.0.0.1"
    val multicastPort = 2000
    val packetSize = 20016
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initKoin {

            androidContext(this@MainActivity.applicationContext)
        }

        val permissionManeger = PermissionManeger(this)

        permissionManeger.askPermissions(
            PermissionManeger.PERMISSION.CONTACTS_PERMISSION,
            PermissionManeger.PERMISSION.CAMERA_PERMISSION,
            PermissionManeger.PERMISSION.BLUETOOTH_PERMISSION,
            PermissionManeger.PERMISSION.STORAGE_PERMISSION
        )

        setContent {

          /*  udpReceiver = UdpMulticastReceiver(
                address = multicastAddress,
                port = multicastPort,
                packetSize = packetSize
            ) { frame ->
                processFrame(frame)
            }*/

          //  udpReceiver.startReceiving()

           // VideoStream()
            /*    InputStreamHttpPlayer(
                    "http://192.168.1.150:0000/ts.mpd",
                   modifier = Modifier.fillMaxSize()
                )*/
            App().AppContent()

            /*package com

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbEndpoint
import android.hardware.usb.UsbManager
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TsdScannerManager(private val context: Context) {

    // Константы
    companion object {
        private const val ACTION_USB_PERMISSION = "com.example.USB_PERMISSION"
        private const val VENDOR_ID = 1234 // Укажите ваш Vendor ID
        private const val PRODUCT_ID = 5678 // Укажите ваш Product ID
    }

    // Переменные для работы с USB
    private val usbManager: UsbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    private var usbDevice: UsbDevice? = null
    private var usbConnection: UsbDeviceConnection? = null
    private var usbEndpointIn: UsbEndpoint? = null

    // BroadcastReceiver для обработки разрешений
    private val usbPermissionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ACTION_USB_PERMISSION) {
                val granted = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)
                usbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                if (granted && usbDevice != null) {
                    setupUsbConnection(usbDevice!!)
                } else {
                    println("Доступ к USB-устройству отклонён")
                }
            }
        }
    }

    init {
        // Регистрируем BroadcastReceiver для обработки разрешений
        val filter = IntentFilter(ACTION_USB_PERMISSION)
        context.registerReceiver(usbPermissionReceiver, filter)
    }

    // Функция для проверки устройства и запроса разрешения
    fun scanWithUsbScanner() {
        val deviceList = usbManager.deviceList
        usbDevice = deviceList.values.firstOrNull { it.vendorId == VENDOR_ID && it.productId == PRODUCT_ID }

        if (usbDevice == null) {
            println("USB No compatible USB device found")
            println("Не найдено совместимых USB-устройств")
            return
        }

        if (!usbManager.hasPermission(usbDevice)) {
            val permissionIntent = PendingIntent.getBroadcast(
                context, 0, Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_MUTABLE
            )
            usbManager.requestPermission(usbDevice, permissionIntent)
        } else {
            setupUsbConnection(usbDevice!!)
        }
    }

    // Установка соединения с USB-устройством
    private fun setupUsbConnection(device: UsbDevice) {
        val usbInterface = device.getInterface(0) // Используем первый интерфейс
        usbEndpointIn = usbInterface.getEndpoint(0) // Входящий поток данных

        usbConnection = usbManager.openDevice(device)
        usbConnection?.claimInterface(usbInterface, true)

        if (usbConnection != null) {
            println("USB device connected and ready for scanning")
            readUsbData()
        } else {
            println("USB Failed to open USB device connection")
            println("Не удалось подключиться к устройству")
        }
    }

    // Чтение данных из USB-устройства
    private fun readUsbData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val buffer = ByteArray(64) // Размер буфера
                while (true) {
                    val bytesRead = usbConnection?.bulkTransfer(usbEndpointIn, buffer, buffer.size, 1000)
                    if (bytesRead != null && bytesRead > 0) {
                        val scannedData = String(buffer, 0, bytesRead)
                        println("USB Scanned data: $scannedData")
                        showToastOnMainThread("Отсканировано: $scannedData")
                    }
                }
            } catch (e: Exception) {
                println("USB Error reading data: ${e.message}")
                showToastOnMainThread("Ошибка при чтении данных")
            }
        }
    }

    // Отображение сообщений из потока в основной поток
    private fun showToastOnMainThread(message: String) {
        (context as? android.app.Activity)?.runOnUiThread {

            println("ERROR ${message}")

            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    // Освобождение ресурсов
    fun closeConnection() {
        usbConnection?.releaseInterface(usbDevice?.getInterface(0))
        usbConnection?.close()
        println("USB connection closed")
    }
}*/


        }
    }


}
val bitmap = mutableStateOf<Bitmap?>(null)



// Вызываем в обработчике frame:
private fun processFrame(frame: ByteArray) {
    Log.d("Received frame", "Processing frame of size: ${frame.size}")
   GlobalScope.launch {
        decodeMjpegFrame(frame)
    }
}

// Обратите внимание на использование compose, чтобы отображать обновления в реальном времени:
@Composable
fun VideoStream() {
    if (bitmap.value != null) {
        Image(
            bitmap = bitmap.value!!.asImageBitmap(),
            contentDescription = null,
            Modifier.fillMaxSize()
        )
    }
}
class UdpMulticastReceiver(
    private val address: String,
    private val port: Int,
    private val packetSize: Int,
    private val onFrameReceived: (ByteArray) -> Unit
) {
    private var socket: MulticastSocket? = null
    private var isReceiving = false


    private var frameBuffer = ByteArray(0)

    fun startReceiving() {
        isReceiving = true

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val group = InetAddress.getByName(address)
                socket = MulticastSocket(port).apply {
                    joinGroup(group)
                }

                val buffer = ByteArray(packetSize)

                while (isReceiving) {
                    try {
                        val packet = DatagramPacket(buffer, buffer.size)

                        // Получаем пакет
                        socket?.receive(packet)

                        // Добавляем данные в буфер
                        val receivedData = packet.data.copyOf(packet.length)
                        frameBuffer += receivedData

                        // Проверяем, есть ли конец кадра (FFD9)
                        val endIndex = frameBuffer.indexOfSequence(byteArrayOf(0xFF.toByte(), 0xD9.toByte()))

                        if (endIndex != -1) {
                            // Извлекаем полный кадр
                            val fullFrame = frameBuffer.copyOfRange(0, endIndex + 2)

                            // Передаем кадр в обработчик
                            withContext(Dispatchers.Main) {
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


suspend fun decodeMjpegFrame(data: ByteArray) {
    withContext(Dispatchers.IO) {
        try {
            // Пример: замените "BoundaryString" на ваше реальное значение границы
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
