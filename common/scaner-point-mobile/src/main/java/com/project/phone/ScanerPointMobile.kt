package com.project.phone


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import device.common.ScanConst
import org.koin.mp.KoinPlatform.getKoin


@Composable
fun MainScreen(
    viewModel: ScanerPointMobileViewModel = ScanerPointMobileViewModel(getKoin().get()) // или другой способ
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ScanerPointMobileEffect.OpenScannerSettings -> {
                    val intent = Intent(ScanConst.LAUNCH_SCAN_SETTING_ACITON)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }
        }
    }

    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Column(verticalArrangement = Arrangement.Top) {
                Text(text = "Bar type:")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(Color(0x20888888)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = uiState.barType, style = MaterialTheme.typography.body1)
                }

                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Scan result:")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x20888888))
                        .heightIn(min = 50.dp)
                ) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Text(
                            text = uiState.scanResult,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = uiState.autoScanEnabled, onCheckedChange = {
                        viewModel.handleEvent(MainEvent.ToggleAutoScan)
                    })
                    Text(text = "Auto Scan", style = MaterialTheme.typography.body1)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Button(onClick = {
                        viewModel.handleEvent(MainEvent.ScanOn)
                    }, modifier = Modifier.weight(1f)) {
                        Text(text = "Scan On")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        viewModel.handleEvent(MainEvent.ScanOff)
                    }, modifier = Modifier.weight(1f)) {
                        Text(text = "Scan Off")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (uiState.showEnableDialog) {
                    AlertDialog(
                        onDismissRequest = { },
                        title = { Text(text = "Scanner Disabled") },
                        text = { Text(text = "Your scanner is disabled. Do you want to enable it?") },
                        confirmButton = {
                            TextButton(onClick = {
                                viewModel.handleEvent(MainEvent.EnableScannerClicked(scope))
                            }) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                viewModel.handleEvent(MainEvent.DismissDialog)
                            }) {
                                Text("Cancel")
                            }
                        }
                    )
                }
            }
        }
    }
}