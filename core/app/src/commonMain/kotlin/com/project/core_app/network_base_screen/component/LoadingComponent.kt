package com.project.core_app.network_base_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingComponent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(modifier = Modifier.align(Alignment.Center)) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterHorizontally)
            )
           Text("Loading...", modifier = Modifier.align(Alignment.CenterHorizontally))
        }

    }
}