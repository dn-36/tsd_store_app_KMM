package com.profile.profile.screens.main_refactor.screens.warehouse.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen.ArrivalAndConsumptionScreen
import com.profile.profile.screens.main_refactor.screens.warehouse.component.WarehouseComponent
import com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel.WarehouseIntents
import com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel.WarehouseViewModel
import com.project.chats.ProfileScreensApi
import com.project.chats.WarehouseScreensApi
import com.project.core_app.network_base_screen.NetworkScreen
import com.project.`printer-api`.PrinterScreensApi
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.cancel
import project.core.resources.plus
import project.core.resources.update_pencil

class WarehouseScreen : NetworkScreen(

    WarehouseComponent ( getKoin().get() )

)