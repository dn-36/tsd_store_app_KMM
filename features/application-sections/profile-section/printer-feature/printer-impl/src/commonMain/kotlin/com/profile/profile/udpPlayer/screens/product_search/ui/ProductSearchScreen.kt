package com.profile.profile.udpPlayer.screens.product_search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.udpPlayer.screens.product_search.viewmodel.ProductSearchIntent
import org.example.project.presentation.feature.qr_code.screens.product_search.viewmodel.ProductSearchViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.search

object ProductSearchScreen : Screen {
   val viewModel: ProductSearchViewModel = ProductSearchViewModel(getKoin().get())

    @Composable
  override  fun Content() {
        val state by viewModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        viewModel.proccesIntent(ProductSearchIntent.SetScreen(scope))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = state.searchText,
                    onValueChange = {
                        viewModel.proccesIntent(ProductSearchIntent.EnterSearchProduct(it))
                                    },
                    label = { Text("Поиск") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.search),
                        contentDescription = "Search",
                        modifier = Modifier.size(30.dp).clickable {

                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 2. Список результатов поиска
            LazyColumn {
                items(state.items.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                viewModel.proccesIntent(
                                    ProductSearchIntent.SelectProduct(state.items.get(index))
                                )
                            },
                        elevation = 4.dp
                    ) {
                        Text(
                            text = state.items[index].title,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}






