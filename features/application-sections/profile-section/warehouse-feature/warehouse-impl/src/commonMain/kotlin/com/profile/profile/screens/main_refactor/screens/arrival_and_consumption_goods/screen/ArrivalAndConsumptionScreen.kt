package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.AddProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ArrivalAndConsumptionComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.CountProductComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.DataEntryComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.Item
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ScannerComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.ListProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionIntents
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionViewModel
import com.project.core_app.network_base_screen.NetworkScreen
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.koin.mp.KoinPlatform
import org.koin.mp.KoinPlatform.getKoin

class ArrivalAndConsumptionScreen : NetworkScreen(

    ArrivalAndConsumptionComponent ( getKoin().get() )

)