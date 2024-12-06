package component.data_entry_goods_and_services.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.utils.boxHeight
import component.data_entry_goods_and_services.viewmodel.DataEntryGoodsAndServicesIntents
import component.data_entry_goods_and_services.viewmodel.DataEntryGoodsAndServicesViewModel
import model.CategoryGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntryGodsAndServicesComponent (

    val onClickBack:() -> Unit,

    val listSystemCategory: List<SystemCategoryGoodsServicesModel>,

    val listCategory: List<CategoryGoodsServicesModel>,

    val listUnitsMeasurement: List<UnitGoodsAndServicesModel>

    ) {

    val viewModel = DataEntryGoodsAndServicesViewModel()

    @Composable

    fun Content() {

        val scroll = rememberScrollState()

        viewModel.processIntents( DataEntryGoodsAndServicesIntents.SetScreen( listCategory,

            listSystemCategory, listUnitsMeasurement ))

        Box( modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp).verticalScroll(scroll)) {

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

                    value = viewModel.state.name,

                    onValueChange = {

                        viewModel.processIntents(DataEntryGoodsAndServicesIntents.InputTextName(it))

                    },

                    label = { Text("Название", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.price,

                    onValueChange = {

                        viewModel.processIntents(

                            DataEntryGoodsAndServicesIntents.InputTextPrice(it))

                    },

                    label = { Text("Цена", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.category,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryGoodsAndServicesIntents.InputTextCategory(inputText,

                                listCategory.filter {

                                    val name = it.name.orEmpty()

                                    name.contains(inputText, ignoreCase = true)

                                }))

                    },

                    label = { Text("Категория") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuCategory)

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

                if (viewModel.state.selectedCategory != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedCategory!!.name?:"Имя не указано",
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

                                        DataEntryGoodsAndServicesIntents.DeleteSelectedCategory

                                    )

                                })
                    }
                }

                if (viewModel.state.expendedCategory) {

                    Box(modifier = Modifier.fillMaxWidth().height(

                        boxHeight( viewModel.state.listFilteredCategory.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.listFilteredCategory)

                            { index, item ->

                                    Text(item.name?:"Имя не указано",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                            .padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(

                                                    DataEntryGoodsAndServicesIntents.SelectCategory(

                                                        item

                                                    )
                                                )

                                            })


                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.systemCategory,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryGoodsAndServicesIntents.InputTextSystemCategory(inputText,

                                listSystemCategory.filter {

                                    val name = it.name.orEmpty()

                                    name.contains(inputText, ignoreCase = true)
                                }))

                    },

                    label = { Text("Системная категория") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuSystemCategory)

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

                if (viewModel.state.selectedSystemCategory != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedSystemCategory!!.name?:"Имя не указано",
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

                                        DataEntryGoodsAndServicesIntents.DeleteSelectedSystemCategory

                                    )

                                })
                    }
                }

                if (viewModel.state.expendedSystemCategory) {

                    Box(modifier = Modifier.fillMaxWidth().height(

                        boxHeight( viewModel.state.listFilteredSystemCategory.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.listFilteredSystemCategory)

                            { index, item ->

                                    Text(item.name?:"Имя не указано",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                            .padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(

                                                    DataEntryGoodsAndServicesIntents.SelectSystemCategory(

                                                        item
                                                    )
                                                )

                                            })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.uniMeasurement,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryGoodsAndServicesIntents.InputTextUnit(inputText,

                                listUnitsMeasurement.filter {

                                    val name = it.name.orEmpty()

                                    name.contains(inputText, ignoreCase = true)
                                }))

                    },

                    label = { Text("Единица измерения") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuUnits)

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

                if (viewModel.state.selectedUnit != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedUnit!!.name?:"Имя не указано",
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

                                        DataEntryGoodsAndServicesIntents.DeleteSelectedUnit )

                                })
                    }
                }

                if (viewModel.state.expendedUnits) {

                    Box(modifier = Modifier.fillMaxWidth().height(

                        boxHeight( viewModel.state.listFilteredUnits.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.listFilteredUnits)

                            { index, item ->

                                Text(item.name?:"Имя не указано",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectUnit(

                                                    item
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.productLink,

                    onValueChange = {

                        viewModel.processIntents(

                            DataEntryGoodsAndServicesIntents.InputTextProductLink(it))

                    },

                    label = { Text("Ссылка на товар", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.sku,

                    onValueChange = {

                        viewModel.processIntents(DataEntryGoodsAndServicesIntents.InputTextSku(it))

                    },

                    label = { Text("sku", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.videoYouTube,

                    onValueChange = {

                        viewModel.processIntents(

                            DataEntryGoodsAndServicesIntents.InputTextVideoYouTube(it))

                    },

                    label = { Text("Видео YouTube", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.forSale,

                    onValueChange = {  },

                    label = { Text("На продажу") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuForSale)

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

                if (viewModel.state.expendedForSale) {

                    Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.listFilteredUnits)

                            { index, item ->

                                Text(item.name?:"Имя не указано",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectForSale(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.displayOnSite,

                    onValueChange = {  },

                    label = { Text("Отображать на сайте") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuDisplayOnSite)

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

                if (viewModel.state.expendedDisplayOnSite) {

                    Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.listFilteredUnits)

                            { index, item ->

                                Text(item.name?:"Имя не указано",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectDisplayOnSite(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.underOrder,

                    onValueChange = {  },

                    label = { Text("Под заказ") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuUnderOrder)

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

                if (viewModel.state.expendedUnderOrder) {

                    Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.listFilteredUnits)

                            { index, item ->

                                Text(item.name?:"Имя не указано",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectUnderOrder(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.stock,

                    onValueChange = {  },

                    label = { Text("В наличии") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuIsStock)

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

                if (viewModel.state.expendedIsStock) {

                    Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.listFilteredUnits)

                            { index, item ->

                                Text(item.name?:"Имя не указано",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectIsStock(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.displayStock,

                    onValueChange = {  },

                    label = { Text("Отображать наличие") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuDisplayStock)

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

                if (viewModel.state.expendedDisplayStock) {

                    Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.listFilteredUnits)

                            { index, item ->

                                Text(item.name?:"Имя не указано",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectDisplayStock(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))


                Button(

                    onClick = {

                    },

                    modifier = Modifier
                        .clip(RoundedCornerShape(70.dp))
                        .height(40.dp)
                        .fillMaxWidth()

                ) {

                    Text(text = "Далее")

                }

            }
        }
    }
}