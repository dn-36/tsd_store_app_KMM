package com.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.viewmodel.ProductsMenuIntents
import com.viewmodel.ProductsMenuViewModel
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseSection

class ProductsMenuScreen : Screen {

    val viewModel = ProductsMenuViewModel()

    @Composable

    override fun Content() {

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp)) {

                Text("Меню в категории товаров", color = Color.Black, fontSize = 20.sp)

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.processIntents(ProductsMenuIntents.Categories) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .fillMaxWidth()

                ) {
                    Text(text = "Категории", modifier = Modifier)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Единицы измерения")
                }
            }

            Box(modifier = Modifier.align(Alignment.BottomCenter)) {


                MenuBottomBarWarehouse().Content(MenuBottomBarWarehouseSection.PRODUCTS)

            }

        }
    }

}