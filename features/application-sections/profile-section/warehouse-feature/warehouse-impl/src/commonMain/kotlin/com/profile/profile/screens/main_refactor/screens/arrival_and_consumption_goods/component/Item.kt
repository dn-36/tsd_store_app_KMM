package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.util.formatDateTime
import com.project.network.arrival_goods.model.StoreResponse

@Composable
fun Item (item: StoreResponse) {

    Box() {

        Row(modifier = Modifier.fillMaxWidth()) {

            Column {

                if (item.is_push == 1) {

                    Text("Приход", fontSize = 17.sp, fontWeight = FontWeight.Bold)

                }
                else{

                    Text("Расход", fontSize = 17.sp, fontWeight = FontWeight.Bold)

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Контрагент: ${item.contragent.name}", fontSize = 15.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Text("Склад: ${item.store.name}", fontSize = 15.sp)

            }

        }

        Text( formatDateTime(item.created_at), fontSize = 13.sp, modifier = Modifier.align(Alignment.BottomEnd))

    }

}