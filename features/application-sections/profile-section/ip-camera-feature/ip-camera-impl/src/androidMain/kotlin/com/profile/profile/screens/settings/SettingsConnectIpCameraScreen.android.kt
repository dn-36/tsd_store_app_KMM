package com.profile.profile.screens.settings

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import org.koin.mp.KoinPlatform.getKoin


actual fun openWifiSettings() {
    val context: Context = getKoin().get()
    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}

actual fun saveToClipboard(label: String, text: String) {
    val context:Context = getKoin().get()
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, text)
    clipboard.setPrimaryClip(clip)

    Toast.makeText(context,"Пороль скопирован",Toast.LENGTH_SHORT).show()
}

