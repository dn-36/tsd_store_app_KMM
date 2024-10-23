package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.util.formatDateTime
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.cancel
import project.core.resources.update_pencil

@Composable
fun Item (item: StoreResponseArrivalAndConsumption, onDelete:( ui:String ) -> Unit, onUpdate:(item:StoreResponseArrivalAndConsumption) -> Unit) {

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

                Text("Контрагент: ${item.contragent!!.name}", fontSize = 15.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Text("Склад: ${item.store!!.name}", fontSize = 15.sp)

                Spacer(modifier = Modifier.height(8.dp))

            }

        }

        Text( formatDateTime(item.created_at!!), fontSize = 13.sp, modifier = Modifier.align(Alignment.BottomEnd))

        Row (modifier = Modifier.align(Alignment.TopEnd)){

            Image(painter = painterResource(Res.drawable.update_pencil), contentDescription = null,

                modifier = Modifier.size(15.dp).clickable(
                    indication = null, // Отключение эффекта затемнения
                    interactionSource = remember { MutableInteractionSource() })

                { onUpdate(item) }
            )

            Spacer(modifier = Modifier.width(15.dp))

            Image(painter = painterResource(Res.drawable.cancel), contentDescription = null,

                modifier = Modifier.size(15.dp).clickable(
                    indication = null, // Отключение эффекта затемнения
                    interactionSource = remember { MutableInteractionSource() })

                { onDelete(item.ui!!) }
            )

        }

    }

}