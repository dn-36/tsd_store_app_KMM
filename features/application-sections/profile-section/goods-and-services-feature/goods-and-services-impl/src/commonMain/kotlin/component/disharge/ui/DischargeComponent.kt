package component.disharge.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.disharge.viewmodel.DischargeIntents
import component.disharge.viewmodel.DischargeViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.ready

class DischargeComponent (

    val onClickReady:( isBu: Int, manufacturer: String, numberManufacturer: String,

            postavka: String) -> Unit

 ) {

    val viewModel = DischargeViewModel()

    @Composable

     fun Content() {

        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(

                        painter = painterResource(Res.drawable.back), contentDescription = null,

                        modifier = Modifier.size(20.dp).clickable(

                            indication = null, // Отключение эффекта затемнения

                            interactionSource = remember { MutableInteractionSource() })

                        { }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Товары и услуги", color = Color.Black, fontSize = 20.sp)

                }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField (

                        value = viewModel.state.manufacturer,

                        onValueChange = {

                           viewModel.processIntents(DischargeIntents.InputTextManufacturer(it))

                        },

                        label = { Text("Производитель", fontSize = 15.sp) },

                        textStyle = TextStyle(fontSize = 15.sp),

                        modifier = Modifier.fillMaxWidth().heightIn(min = 45.dp)

                    )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.numberManufacturer,

                    onValueChange = {

                         viewModel.processIntents(DischargeIntents.InputTextNumberManufacturer(it))

                    },

                    label = { Text("Номер производителя", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier.fillMaxWidth().heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.postavka,

                    onValueChange = {

                        viewModel.processIntents(DischargeIntents.InputTextPostavka(it))

                    },

                    label = { Text("Срок поставки", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier.fillMaxWidth().heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(15.dp))

                Row ( modifier = Modifier.clickable(

                    indication = null, // Отключение эффекта затемнения

                    interactionSource = remember { MutableInteractionSource() })

                { viewModel.processIntents(DischargeIntents.IsBu) },

                    verticalAlignment = Alignment.CenterVertically ) {

                    Text("Б/у", fontSize = 18.sp)

                    Spacer(modifier = Modifier.width(15.dp))

                    if ( viewModel.state.isBu ) {

                        Image(
                            painterResource(Res.drawable.ready), contentDescription = null,

                            modifier = Modifier.size(15.dp)
                        )

                    }

                }


                Spacer ( modifier = Modifier.height(30.dp) )

                Button (

                    onClick = { onClickReady( if (viewModel.state.isBu) 1 else 0 ,

                        viewModel.state.manufacturer, viewModel.state.numberManufacturer,

                        viewModel.state.postavka ) },

                    modifier = Modifier
                        .clip(RoundedCornerShape(70.dp))
                        .height(40.dp)
                        .fillMaxWidth()

                ) {

                    Text(text = "Готово")

                }
            }
        }
    }
}