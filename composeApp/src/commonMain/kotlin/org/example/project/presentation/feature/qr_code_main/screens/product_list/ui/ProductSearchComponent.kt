package org.example.project.presentation.feature.qr_code_main.screens.product_list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

import org.example.project.presentation.feature.qr_code_main.screens.product_list.viewmodel.ProductSearchIntent
import org.example.project.presentation.feature.qr_code_main.screens.product_list.viewmodel.ProductSearchViewModel
import org.jetbrains.compose.resources.painterResource
import tsdstorekmm.composeapp.generated.resources.Res
import tsdstorekmm.composeapp.generated.resources.search

class SearchScreen() : Screen {
   private val viewModel: ProductSearchViewModel = ProductSearchViewModel()
    @Composable
  override  fun Content() {

        val state by viewModel.state.collectAsState()
        viewModel.proccesIntent(ProductSearchIntent.Search)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 1. EditText с кнопкой "search"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = state.searchText,
                    onValueChange = { viewModel.proccesIntent(ProductSearchIntent.EnterSearchProduct(it)) },
                    label = { Text("Поиск") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { viewModel.proccesIntent(ProductSearchIntent.Search) }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.search),
                        contentDescription = "Search",
                        modifier = Modifier.clickable {
                            viewModel.proccesIntent(ProductSearchIntent.Search)
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
                            .clickable { /* Действие при выборе элемента */ },
                        elevation = 4.dp
                    ) {
                        Text(
                            text = state.items[index],
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}






