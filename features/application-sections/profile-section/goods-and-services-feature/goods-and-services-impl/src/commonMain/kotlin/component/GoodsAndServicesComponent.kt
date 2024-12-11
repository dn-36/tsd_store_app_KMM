package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.components.PlusButton
import com.project.core_app.components.delete_component.DeleteComponent
import com.project.core_app.network_base_screen.NetworkComponent
import component.additional_information.AdditionalInformationComponent
import component.data_entry_goods_and_services.ui.DataEntryGodsAndServicesComponent
import component.disharge.ui.DischargeComponent
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.update_pencil
import viewmodel.GoodsAndServicesIntents
import viewmodel.GoodsAndServicesViewModel

class GoodsAndServicesComponent( val sku: String, override val viewModel: GoodsAndServicesViewModel

    ): NetworkComponent {

    @Composable
    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(GoodsAndServicesIntents.SetScreen( scope, sku ))

        Box( modifier = Modifier.fillMaxSize().background(Color.White) ) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(

                        painter = painterResource(Res.drawable.back), contentDescription = null,

                        modifier = Modifier.size(20.dp).clickable(

                            indication = null, // Отключение эффекта затемнения

                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(GoodsAndServicesIntents.Back) }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Товары и услуги", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(){

                    itemsIndexed(viewModel.state.listProducts){ index, it ->

                        Box(modifier = Modifier.pointerInput(true) {

                            detectTapGestures(

                                onPress = {

                                    if (  viewModel.state.listAlphaTools.size > index &&

                                        viewModel.state.listAlphaTools[index] == 1f ) {

                                        viewModel.processIntents(

                                            GoodsAndServicesIntents.OnePressItem)
                                    }
                                },

                                onLongPress = {

                                    viewModel.processIntents(

                                        GoodsAndServicesIntents.LongPressItem(index))

                                },
                            )

                        }) {

                                Column(modifier = Modifier.fillMaxWidth()) {

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = "${index + 1}. ${it.name ?: "Имя не указано"}",

                                        fontSize = 16.sp,

                                        modifier = Modifier.fillMaxWidth(0.85f)
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                        Box(

                                            modifier = Modifier.height(1.dp).fillMaxWidth()

                                                .background(Color.LightGray)
                                        )

                                }
                            if ( viewModel.state.listAlphaTools.size > index &&

                                 viewModel.state.listAlphaTools[index] == 1f

                            ) {

                                Row(modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)) {

                                    Image(painter = painterResource(Res.drawable.update_pencil),
                                        contentDescription = null,
                                        modifier = Modifier.size(17.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })
                                            {

                                                viewModel.processIntents(

                                                    GoodsAndServicesIntents.OpenUpdateDataEntry(

                                                        scope, it))

                                            })

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Image(painter = painterResource(Res.drawable.cancel),
                                        contentDescription = null,
                                        modifier = Modifier.size(15.dp)
                                            .clickable(
                                                indication = null,
                                                interactionSource = remember {

                                                    MutableInteractionSource() })
                                            {

                                                viewModel.processIntents(

                                                    GoodsAndServicesIntents.OpenDeleteComponent(it))

                                            })
                                }
                            }
                        }
                    }
                }
            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {

                PlusButton {

                    viewModel.processIntents(GoodsAndServicesIntents.OpenCreateDataEntry( scope ))

                }

            }

        }

        if ( viewModel.state.isVisibilityDataEntry ) {

           // DischargeComponent().Content()

            DataEntryGodsAndServicesComponent( onClickBack = {

            viewModel.processIntents(GoodsAndServicesIntents.BackFromDataEntry)} ,

                onClickNext = { name: String, video_youtube: String, ediz_id: Int?,

                                  category_id: Int?, is_product: Int, is_sale: Int,

                                  system_category_id: Int?, is_view_sale: Int, is_order: Int,

                                  is_store: Int, is_store_view: Int, sku: String,

                                  text_image, price: Float?, tags: List<String>,

                                  variantes: List<String>, divisions: String, image ->

                                viewModel.processIntents(

                                    GoodsAndServicesIntents.Next( name,

                                        video_youtube, ediz_id, category_id, is_product, is_sale,

                                    system_category_id, is_view_sale, is_order, is_store,

                                    is_store_view, sku, text_image, price, tags, variantes,

                                        divisions, image ) )
                },

                listSystemCategory = viewModel.state.listSystemCategory,

                listCategory = viewModel.state.listCategory,

                listUnitsMeasurement = viewModel.state.listUnitsMeasurement,

                sku = sku, updateItem = viewModel.state.updateItem ).Content()

        }

        else if ( viewModel.state.isVisibilityDeleteComponent ) {

            DeleteComponent(

                name = "",

                onClickDelete = { viewModel.processIntents(

                    GoodsAndServicesIntents.DeleteGoodOrService(scope)) },

                onClickNo = { viewModel.processIntents(GoodsAndServicesIntents.NoDelete) }

            ).Content()

        }

        else if ( viewModel.state.isVisibilityAdditionalInformationComponent ) {

            AdditionalInformationComponent( onClickDischarge = {

            viewModel.processIntents(GoodsAndServicesIntents.Discharge)},

                onClickCreateOrUpdate = { if ( viewModel.state.updateItem == null )

                    viewModel.processIntents(

                    GoodsAndServicesIntents.CreateGoodOrService(scope)) else

                        viewModel.processIntents(

                            GoodsAndServicesIntents.UpdateGoodOrService(scope)) },

                onClickBack = { viewModel.processIntents(

                    GoodsAndServicesIntents.BackFromAdditionalInformation) }).Content()

        }

        else if ( viewModel.state.isVisibilityDischargeComponent ) {

            DischargeComponent( updateItem = viewModel.state.updateItem,

                onClickReady = { isBu, manufacturer, numberManufacturer,

                                                 postavka ->

                viewModel.processIntents(GoodsAndServicesIntents.ReadyDischarge( isBu, manufacturer,

                    numberManufacturer, postavka ))

            }, onClickBack = { viewModel.processIntents(

                    GoodsAndServicesIntents.BackFromDischarge) } ).Content()

        }

    }

}