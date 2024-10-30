package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.network_base_screen.NetworkComponent
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import project.core.resources.Res
import project.core.resources.back
import util.formatDateTime
import viewmodel.CRMIntents
import viewmodel.CRMViewModel

class CRMComponent ( override val viewModel: CRMViewModel ) : NetworkComponent {

    @Composable

    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(CRMIntents.getIncomingCRM(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(CRMIntents.Back) }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("CRM", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally,

                        modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(CRMIntents.SelectTypeCRM(1)) }) {

                        Text("Входящие", fontSize = 18.sp)

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier.height(1.dp).width(100.dp)

                                .background(
                                    if (viewModel.state.isIncoming) Color.Gray
                                    else Color.Transparent
                                )
                        )

                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally,

                        modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(CRMIntents.SelectTypeCRM(2)) }) {

                        Text("Исходящие", fontSize = 18.sp)

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier.height(1.dp).width(110.dp)

                                .background(
                                    if (viewModel.state.isOutgoing) Color.Gray
                                    else Color.Transparent
                                )
                        )

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                if (viewModel.state.isIncoming) {

                    LazyColumn {

                        items(viewModel.state.listIncomingCRM) { item ->

                            Column(modifier = Modifier.fillMaxWidth()) {

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    "Клиент/Проект - ${
                                        if (item.entity != null)

                                            item.entity.name else "Не указано"
                                    } / ${

                                        if (item.projects != null)

                                            item.projects.name else "Не указано"
                                    } "
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "Юр.лицо исполнитель - ${
                                        if (item.entity_our != null)

                                            item.entity_our.name else "Не указано"
                                    }"
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Дата - ${formatDateTime(item.created_at)}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Прайс - ${item.price}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier.height(1.dp).fillMaxWidth()

                                        .background(Color.Gray)
                                )

                            }

                        }
                    }

                } else if (viewModel.state.isOutgoing) {

                    LazyColumn {

                        items(viewModel.state.listOutgoingCRM) { item ->

                            Column(modifier = Modifier.fillMaxWidth()) {

                                Text(
                                    "Клиент/Проект - ${
                                        if (item.entity != null)

                                            item.entity.name else "Не указано"
                                    } / ${

                                        if (item.projects != null)

                                            item.projects.name else "Не указано"
                                    }"
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "Юр.лицо исполнитель - ${
                                        if (item.entity_our != null)

                                            item.entity_our.name else "Не указано"
                                    }"
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Дата - ${formatDateTime(item.created_at)}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Прайс - ${item.price}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier.height(1.dp).fillMaxWidth()

                                        .background(Color.Gray)
                                )

                            }

                        }
                    }

                }


            }
        }
    }
}