package component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.components.PlusButton
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.core_app.network_base_screen.NetworkViewModel
import component.data_entry_goods_and_services.ui.DataEntryGodsAndServicesComponent
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import viewmodel.GoodsAndServicesIntents
import viewmodel.GoodsAndServicesViewModel

class GoodsAndServicesComponent(override val viewModel: GoodsAndServicesViewModel): NetworkComponent {

    @Composable
    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(GoodsAndServicesIntents.GetGoodsAndServices(scope))

        Box( modifier = Modifier.fillMaxSize().background(Color.White)) {

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

                    }

                }

            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {

                PlusButton {

                    viewModel.processIntents(GoodsAndServicesIntents.OpenCreateDataEntry( scope ))

                }

            }

        }

        if ( viewModel.state.isVisibilityDataEntry ){

            DataEntryGodsAndServicesComponent( onClickBack = {

            viewModel.processIntents(GoodsAndServicesIntents.BackFromDataEntry)} ,

                listSystemCategory = viewModel.state.listSystemCategory,

                listCategory = viewModel.state.listCategory,

                listUnitsMeasurement = viewModel.state.listUnitsMeasurement ).Content()

        }

    }

}