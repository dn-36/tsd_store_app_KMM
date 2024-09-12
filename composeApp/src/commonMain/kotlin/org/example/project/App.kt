package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.module.core.Klassaa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.module.core.core.NavigatorView
import com.module.core.core.networking.InsultCensorClient
import org.jetbrains.compose.ui.tooling.preview.Preview
import tsdstorekmm.composeapp.generated.resources.Res

import util.onError
import util.onSuccess

@Composable
@Preview
fun App(client: InsultCensorClient) {
    Klassaa()
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

               Res.drawable
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