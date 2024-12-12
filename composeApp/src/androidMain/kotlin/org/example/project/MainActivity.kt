package org.example.project


import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.project.phone.MainScreen
import com.project.phone.PermissionManeger
import org.example.project.app.ui.App
import org.koin.android.ext.koin.androidContext
class MainActivity : ComponentActivity() {


    private val scannedText = mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initKoin {
            androidContext(this@MainActivity.applicationContext)
        }

        val permissionManeger = PermissionManeger(this)

        permissionManeger.askPermissions(
            PermissionManeger.PERMISSION.BLUETOOTH_PERMISSION,
            PermissionManeger.PERMISSION.CONTACTS_PERMISSION,
            PermissionManeger.PERMISSION.CAMERA_PERMISSION,
            PermissionManeger.PERMISSION.LOCATION_PERMISSION
        )
        PermissionManeger(this).askPermissions(PermissionManeger.PERMISSION.BLUETOOTH_PERMISSION)

        setContent {
           // ResizableSquareScreen()
           // App().AppContent()

            MainScreen()

        }
    }
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null && event.unicodeChar != 0) {
            scannedText.value += event.unicodeChar.toChar()
        }
        if (event?.keyCode == KeyEvent.KEYCODE_ENTER) {
            // Сканирование завершено
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    @Composable
    fun ResizableSquareScreen() {
        // Размеры квадрата
        var squareWidth by remember { mutableStateOf(150f) }
        var squareHeight by remember { mutableStateOf(150f) }

        // Границы квадрата, которые будут перетаскиваться
        val borderThickness = 20.dp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width = squareWidth.dp, height = squareHeight.dp)
                    .background(Color.Blue)
            ) {
                // Правая граница
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(borderThickness)
                        .align(Alignment.CenterEnd)
                        .background(Color.Red.copy(alpha = 0.5f))
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                squareWidth = (squareWidth + dragAmount.x).coerceIn(50f, 300f)
                            }
                        }
                )

                // Нижняя граница
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(borderThickness)
                        .align(Alignment.BottomCenter)
                        .background(Color.Green.copy(alpha = 0.5f))
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                squareHeight =
                                    ((squareHeight + dragAmount.y).coerceIn(50f, 300f) * 2)
                            }
                        }
                )
            }
        }
    }

        @Composable
        fun ScannerScreen(
            scannedText: String,
            onRescan: () -> Unit,
            onSubmit: (String) -> Unit
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Scanned QR Code", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = scannedText,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(onClick = onRescan) {
                        Text("Rescan")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { onSubmit(scannedText) }) {
                        Text("Submit")
                    }
                }
            }
        }
    }


