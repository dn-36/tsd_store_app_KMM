package com.project.network.notitfication

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SocketClient(private val userId: String) {

    private val client = HttpClient {
        install(WebSockets)
    }

    private val socketUrl = "ws://delta.online:6001"

    private val channelName = "laravel_database_channel_${userId}:message"

    private var socket: DefaultClientWebSocketSession? = null

    private val _messages = MutableSharedFlow<String>()
    val messages: SharedFlow<String> = _messages

    private val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun connect() {
        coroutineScope.launch {
            try {
                client.webSocket(
                    method = HttpMethod.Get,
                    host = socketUrl.replace("ws://", "").split(":")[0],
                    port = socketUrl.split(":")[2].toInt(),
                    path = "/"
                ) {
                    socket = this

                    println("/////////////////////////------------Connected to WebSocket!---------------///////////////////////////////")

                    send(Frame.Text("{\"subscribe\":\"$channelName\"}"))

                    for (frame in incoming) {
                        when (frame) {
                            is Frame.Text -> {
                                val message = frame.readText()
                                _messages.emit(message) // Отправляем данные в SharedFlow
                                println("Received: $message")
                            }
                            is Frame.Binary -> {
                                println("Binary data received: ${frame.data.size} bytes")
                            }
                            else -> Unit
                        }
                    }
                }
            } catch (e: Exception) {
                println("/////////////////////////------------WebSocket error!---------------///////////////////////////////")

                println("WebSocket error: ${e.message}")
            }
        }
    }

    fun disconnect() {
        coroutineScope.launch {
            socket?.close()
            coroutineScope.cancel()
            println("Disconnected from WebSocket!")
        }
    }

    fun sendMessage(message: String) {
        coroutineScope.launch {
            socket?.send(Frame.Text(message))
        }
    }
}
