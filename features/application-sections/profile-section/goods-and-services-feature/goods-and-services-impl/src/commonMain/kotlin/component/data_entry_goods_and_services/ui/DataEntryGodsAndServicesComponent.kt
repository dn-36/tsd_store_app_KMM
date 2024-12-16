package component.data_entry_goods_and_services.ui

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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import com.project.core_app.utils.boxHeight
import component.data_entry_goods_and_services.viewmodel.DataEntryGoodsAndServicesIntents
import component.data_entry_goods_and_services.viewmodel.DataEntryGoodsAndServicesViewModel
import model.CategoryGoodsServicesModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntryGodsAndServicesComponent (

    val onClickBack:() -> Unit,

    val onClickNext:(

        name: String,
        video_youtube: String,
        ediz_id: Int?,
        category_id: Int?,
        is_product: Int,
        is_sale: Int,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
        system_category_id: Int?,
        is_view_sale: Int,
        is_order: Int,
        is_store: Int,
        is_store_view: Int,
        is_test: Int?,
        is_arenda: Int?,
        is_zakaz: Int?,
        is_ves: Int?,
        is_serial_nomer: Int?,
        is_date_fabrica: Int?,
        is_markirovka: Int?,
        //is_bu: 0/1 (Б/у или нет)
        //is_ob_zvonok: 0/1 (обратный звонок по товару)
        //metka_system: '' (Системная метка)
        sku: String,
        text_image: String,
        //creater: '' (Производитель)
        //nomer_creater: '' (Номер произовдителя)
        //postavka: '' (Срок поставки)
        //url: '' (slug для ссылки на английском )
        price: Float?,
        tags: List<String>, //(пока что пустой массив отправлять)
        variantes: List<String>, //(пока что пустой массив отправлять)
        divisions: String, //(пока что пустой строкой отправлять)
        image_upload: ImageBitmap?

            ) -> Unit,

    val create:()-> Unit,

    val listSystemCategory: List<SystemCategoryGoodsServicesModel>,

    val listCategory: List<CategoryGoodsServicesModel>,

    val listUnitsMeasurement: List<UnitGoodsAndServicesModel>,

    val sku: String,

    val updateItem: ProductGoodsServicesModel?

    ) {

    val viewModel = DataEntryGoodsAndServicesViewModel()

    /*
    is_test: 0/1 (Можно взять на тест)
    is_arenda: 0/1 (Можно взять в аренду)
    is_zakaz: 0/1 (Можно заказать)
     */

    @Composable

    fun Content() {

        val scroll = rememberScrollState()

        val scope = rememberCoroutineScope()

        val multipleImagePicker = rememberImagePickerLauncher(
            scope = scope,
            selectionMode = SelectionMode.Multiple(),
            onResult = { byteArrays ->

                viewModel.state = viewModel.state.copy(
                    image = byteArrays.map {
                        it.toImageBitmap()
                    }.get(0)
                )
            }
        )

        viewModel.processIntents( DataEntryGoodsAndServicesIntents.SetScreen( scope, listCategory,

            listSystemCategory, listUnitsMeasurement, sku, updateItem ))

        Box( modifier = Modifier.fillMaxSize().background(Color.White).clickable(
            indication = null, // Отключение эффекта затемнения
            interactionSource = remember { MutableInteractionSource() })

        {  } ) {

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

                Spacer( modifier = Modifier.height(20.dp) )

                OutlinedTextField(

                    value = viewModel.state.name,

                    onValueChange = {

                        viewModel.processIntents(DataEntryGoodsAndServicesIntents.InputTextName(it))

                    },

                    label = { Text("Название", fontSize = 15.sp) },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = viewModel.state.listColorBorderTF[0], // Цвет границы при фокусе
                        unfocusedBorderColor = viewModel.state.listColorBorderTF[0], // Цвет границы в неактивном состоянии
                        backgroundColor = viewModel.state.listColorBorderTF[0].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier.fillMaxWidth().heightIn(min = 45.dp)

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

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = viewModel.state.listColorBorderTF[1], // Цвет границы при фокусе
                        unfocusedBorderColor = viewModel.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        backgroundColor = viewModel.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

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

                    Box (
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text (
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
                                .clickable (
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

                    value = viewModel.state.unitMeasurement,

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

                OutlinedTextField (

                    value = viewModel.state.selectedGoodOrService.first,

                    onValueChange = {  },

                    label = { Text("Товар или услуга") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuGoodOrService)

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

                if ( viewModel.state.expendedGoodOrService ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listOf("Услуга", "Товар"))

                            { index, item ->

                                Text(item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectGoodOrService(

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

                    value = viewModel.state.selectedForSale.first,

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

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text(item,
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

                    value = viewModel.state.selectedDisplayOnSite.first,

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

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text(item,
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

                    value = viewModel.state.selectedUnderOrder.first,

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

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text(item,
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

                    value = viewModel.state.selectedIsStock.first,

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

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text(item,
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

                OutlinedTextField (

                    value = viewModel.state.selectedDisplayStock.first,

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

                if ( viewModel.state.expendedDisplayStock ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {

                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text( item,
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

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.selectedIsTest.first,

                    onValueChange = {  },

                    label = { Text("Можно взять на тест") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuIsTest)

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

                if ( viewModel.state.expendedIsTest ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {

                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text( item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectIsTest(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.selectedIsSerialNomer.first,

                    onValueChange = {  },

                    label = { Text("Учет по серийному номеру") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuIsSerialNomer)

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

                if ( viewModel.state.expendedIsSerialNomer ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {

                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text( item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectIsSerialNomer(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.selectedDateFabrica.first,

                    onValueChange = {  },

                    label = { Text("Учитывать дату производтсва") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuDateFabrica)

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

                if ( viewModel.state.expendedDateFabrica ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {

                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text( item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectDateFabrica(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.selectedMarkirovka.first,

                    onValueChange = {  },

                    label = { Text("Маркированный товар") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuMarkirovka)

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

                if ( viewModel.state.expendedMarkirovka ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {

                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text( item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectMarkirovka(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.selectedIsZakaz.first,

                    onValueChange = {  },

                    label = { Text("Можно заказать") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuIsZakaz)

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

                if ( viewModel.state.expendedIsZakaz ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {

                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text( item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectIsZakaz(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.selectedIsArenda.first,

                    onValueChange = {  },

                    label = { Text("Можно взять в аренду") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(

                                    DataEntryGoodsAndServicesIntents.MenuIsArenda)

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

                if ( viewModel.state.expendedIsArenda ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {

                            itemsIndexed(listOf("нет", "да"))

                            { index, item ->

                                Text( item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryGoodsAndServicesIntents.SelectIsArenda(

                                                    index
                                                )
                                            )

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row( modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.Center ) {

                    Text(text = "Добавить фото", fontSize = 18.sp,

                        modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { multipleImagePicker.launch() }, textAlign = TextAlign.Center
                    )

                    if (viewModel.state.image != null) {

                        Spacer(modifier = Modifier.width(10.dp))

                        Image(painterResource(Res.drawable.cancel), contentDescription = null,

                            modifier = Modifier.size(15.dp).clickable(

                                indication = null, // Отключение эффекта затемнения

                                interactionSource = remember { MutableInteractionSource() })

                            { viewModel.processIntents(

                                DataEntryGoodsAndServicesIntents.DeleteSelectedPhoto)

                            })

                    }
                }


                if ( viewModel.state.image != null ) {

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                        Image(

                            modifier = Modifier.height(250.dp).fillMaxWidth(0.7f),

                            bitmap = viewModel.state.image!!, contentDescription = null,

                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.descriptionImage,

                    onValueChange = {

                        viewModel.processIntents(

                            DataEntryGoodsAndServicesIntents.InputTextDescriptionImage(it))

                    },

                    label = { Text("Описание изображения", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer ( modifier = Modifier.height(30.dp) )

                Button (

                    onClick = {

                        viewModel.processIntents(DataEntryGoodsAndServicesIntents.CheckTF)

                        if ( viewModel.state.onCheck ) {

                            onClickNext (

                                viewModel.state.name,

                                viewModel.state.videoYouTube,

                                viewModel.state.selectedUnit?.id,

                                viewModel.state.selectedCategory?.id,

                                viewModel.state.selectedGoodOrService.second,

                                viewModel.state.selectedForSale.second,

                                viewModel.state.selectedSystemCategory?.id,

                                viewModel.state.selectedDisplayOnSite.second,

                                viewModel.state.selectedUnderOrder.second,

                                viewModel.state.selectedIsStock.second,

                                viewModel.state.selectedDisplayStock.second,

                                viewModel.state.selectedIsTest.second,

                                viewModel.state.selectedIsArenda.second,

                                viewModel.state.selectedIsZakaz.second,

                                viewModel.state.selectedIsVes.second,

                                viewModel.state.selectedIsSerialNomer.second,

                                viewModel.state.selectedDateFabrica.second,

                                viewModel.state.selectedMarkirovka.second,

                                viewModel.state.sku, viewModel.state.descriptionImage,

                                viewModel.state.price.toFloatOrNull(),

                                emptyList(), emptyList(), "", viewModel.state.image
                            )

                            if ( updateItem == null ) {

                                create()

                            }

                        }
                    },

                    modifier = Modifier
                        .clip(RoundedCornerShape(70.dp))
                        .height(40.dp)
                        .fillMaxWidth()

                ) {

                    Text( text = if ( updateItem != null ) "Далее" else "Создать" )

                }
            }
        }
    }
}