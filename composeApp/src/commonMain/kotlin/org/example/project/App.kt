package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.core.NavigatorView
import org.example.project.core.networking.InsultCensorClient
import org.jetbrains.compose.ui.tooling.preview.Preview

import util.NetworkError
import util.onError
import util.onSuccess

@Composable
@Preview
fun App(client: InsultCensorClient) {
    MaterialTheme {
           CoroutineScope(Dispatchers.IO).launch {
    //   isLoading = true
      // errorMessage = null
         client.registerCreateCode("+79963799050").onSuccess {
             println("//////////////secuesfull//////////////////")
             println(it)
             println("//////////////secuesfull//////////////////")

         }.onError {
             println("//////////////error//////////////////")
             println(it)
             println("//////////////error//////////////////")
         }
      /* client.censorWords(uncensoredText)
           .onSuccess {
               censoredText = it
           }
           .onError {
               errorMessage = it
           }*/
      // isLoading = false
   }
        NavigatorView.Content()

    }
}