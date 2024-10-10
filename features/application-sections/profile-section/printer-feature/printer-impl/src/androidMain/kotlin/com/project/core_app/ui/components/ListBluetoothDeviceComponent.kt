package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.viewmodel.model.StatusBluetoothLoading
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.bluetooth
import project.core.resources.img


@Composable
fun ListBluetoothDevicesComponent(
    list: List<String>,
    clickableItem:(String)->Unit,
    closeSettingsBluetooth:()->Unit,
    searchBluetoothDevice:()->Unit,
    status: StatusBluetoothLoading = StatusBluetoothLoading.LOADING
) {
    Box(Modifier.fillMaxSize()){
    Card(
        modifier = Modifier
            .align(Alignment.Center)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(16.dp), // Форма, которая отбрасывает тень
                clip = false // Если true, то контент будет обрезан по форме
            )
            .clip(RoundedCornerShape(15.dp)) // Применяй clip после shadow
            .background(Color.White) // Обязательно добавь фон
    ) {
        Column (modifier = Modifier.height(525.dp).width(300.dp)) {
            Row {
                Text(
                    "устройства", modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
                if(status == StatusBluetoothLoading.LOADING) {
                    CircularProgressIndicator(
                        Modifier.size(20.dp).align(Alignment.CenterVertically)
                    )

                }
                Spacer(modifier = Modifier.width(130.dp))
                Image(
                    painterResource(Res.drawable.img),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp).align(Alignment.CenterVertically)
                        .clickable {
                            closeSettingsBluetooth()  //clickableItem("product tsd store")
                        }
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            LazyColumn(
                modifier = Modifier.padding(7.dp).height(370.dp).width(270.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                items(list) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 3.dp)
                            .shadow(
                                elevation = 16.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            )
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.White)
                            .clickable {
                                clickableItem(item)
                            }
                        //.border(1.dp, color = Color.Black)
                    ) {
                        Box(Modifier.height(40.dp)) {
                            Text(
                                item,
                                modifier = Modifier.padding(5.dp).align(Alignment.CenterStart)
                            )
                            Image(
                                painter = painterResource(Res.drawable.bluetooth),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .size(20.dp)
                                    .align(Alignment.CenterEnd)
                            )
                        }
                    }
                }
            }
            if (status == StatusBluetoothLoading.SUCCSSFULL) {
                    Text(
                        text = "ПОИСК",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                searchBluetoothDevice()
                            }

                    )
                }



            }
        }


            }
        }
