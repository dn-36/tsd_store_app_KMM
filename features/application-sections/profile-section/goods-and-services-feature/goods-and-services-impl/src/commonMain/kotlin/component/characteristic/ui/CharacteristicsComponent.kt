package component.characteristic.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.utils.boxHeight
import component.characteristic.viewmodel.CharacteristicsIntents
import component.characteristic.viewmodel.CharacteristicsViewModel
import model.ProductGoodsServicesModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.update_pencil

class CharacteristicsComponent (

    val onClickBack:() -> Unit,

    val updateIetem: ProductGoodsServicesModel

 ) {

    val viewModel = CharacteristicsViewModel( getKoin().get(), getKoin().get(), getKoin().get() )

    @Composable

    fun Content() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents( CharacteristicsIntents.SetScreen( scope, updateIetem ))

        Box( modifier = Modifier.fillMaxSize().background(Color.White).clickable(
            indication = null, // Отключение эффекта затемнения
            interactionSource = remember { MutableInteractionSource() })

        {  }) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(

                        painter = painterResource(Res.drawable.back), contentDescription = null,

                        modifier = Modifier.size(20.dp).clickable(

                            indication = null, // Отключение эффекта затемнения

                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Товары и услуги", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = viewModel.state.characteristic,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            CharacteristicsIntents.InputTextCharacteristic(inputText,

                                viewModel.state.listCharacteristics.filter {

                                    val name = it.name.orEmpty()

                                    name.contains(inputText, ignoreCase = true)
                                }))

                    },

                    label = { Text("Характеристика") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(CharacteristicsIntents.MenuCharacteristics)

                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.down_arrow),
                                contentDescription = "Поиск",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                if (viewModel.state.selectedCharacteristic != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedCharacteristic!!.name?:"",
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(8.dp).align(
                                Alignment.CenterStart
                            )
                        )

                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(10.dp)
                                .align(Alignment.TopEnd)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {

                                    viewModel.processIntents(

                                        CharacteristicsIntents.DeleteSelectedCharacteristic

                                    )

                                })
                    }
                }

                if (viewModel.state.expendedCharacteristics) {

                    Box(modifier = Modifier.fillMaxWidth().height(

                        boxHeight( viewModel.state.listFilteredCharacteristics.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {

                            itemsIndexed(viewModel.state.listFilteredCharacteristics)

                            { index, item ->

                                if (item.name != "") {

                                    Text(item.name?:"",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                            .padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(

                                                    CharacteristicsIntents.SelectCharacteristic(

                                                        item
                                                    )
                                                )

                                            })

                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.meaning,

                    onValueChange = {

                        viewModel.processIntents(CharacteristicsIntents.InputTextMeaning(it))

                    },

                    label = { Text("Значение", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier.fillMaxWidth().heightIn(min = 45.dp)

                )

                Spacer ( modifier = Modifier.height(15.dp) )

                Button (

                    onClick = {

                              viewModel.processIntents(

                                  CharacteristicsIntents.CreateCharacteristic( scope ))
                    },

                    modifier = Modifier
                        .clip(RoundedCornerShape(70.dp))
                        .height(40.dp)
                        .fillMaxWidth()

                ) {

                    Text(text = "Добавить")

                }

                Spacer ( modifier = Modifier.height(20.dp) )

                Row( modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceAround ) {

                    Text(text = "Параметр", fontSize = 17.sp, fontWeight = FontWeight.Bold )

                    Text(text = "Значение", fontSize = 17.sp, fontWeight = FontWeight.Bold )

                }

                Spacer ( modifier = Modifier.height(15.dp) )

                LazyColumn {

                    itemsIndexed( viewModel.state.listCreatedParametrs )

                    { index, it ->

                        Box(modifier = Modifier.pointerInput(true) {

                            detectTapGestures(

                                onPress = {
                                    if (  viewModel.state.listAlphaTools.size > index &&

                                        viewModel.state.listAlphaTools[index] == 1f ) {

                                        viewModel.processIntents(

                                            CharacteristicsIntents.OnePressItem )
                                    }
                                },

                                onLongPress = {

                                    viewModel.processIntents(

                                        CharacteristicsIntents.LongPressItem(index))

                                },
                            )

                        }) {

                            Column {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),

                                    horizontalArrangement = Arrangement.SpaceBetween

                                ) {

                                    Text(it.parametr?.name ?: "0", fontSize = 16.sp)

                                    Row( verticalAlignment = Alignment.CenterVertically ) {

                                        Text(it.name ?: "0", fontSize = 16.sp)

                                        if (viewModel.state.listAlphaTools.size > index &&

                                            viewModel.state.listAlphaTools[index] == 1f
                                        ) {

                                            Spacer(modifier = Modifier.width(5.dp))

                                            Image(painter = painterResource(

                                                Res.drawable.update_pencil),

                                                contentDescription = null,

                                                modifier = Modifier.size(12.dp)

                                                    .clickable(
                                                        indication = null, // Отключение затемнения
                                                        interactionSource = remember {

                                                            MutableInteractionSource() })
                                                    {


                                                    })

                                            Spacer(modifier = Modifier.width(20.dp))

                                            Image(painter = painterResource(Res.drawable.cancel),
                                                contentDescription = null,
                                                modifier = Modifier.size(10.dp)
                                                    .clickable(
                                                        indication = null,
                                                        interactionSource = remember {

                                                            MutableInteractionSource() })
                                                    {

                                                    })
                                        }
                                    }

                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Box(
                                    modifier = Modifier.height(1.dp).background(Color.LightGray)

                                        .fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                            }
                        }
                    }
                }
            }
        }
    }
}