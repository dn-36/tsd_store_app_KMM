package component.additional_information

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back

 class AdditionalInformationComponent(

    val onClickDischarge:() -> Unit,

    val onClickCharacteristics:() -> Unit,

    val onClickCreateOrUpdate:() -> Unit,

    val onClickBack:() -> Unit,

    ) {

    @Composable

    fun Content() {

        val scroll = rememberScrollState()

        Box( modifier = Modifier.fillMaxSize().background(Color.White).clickable(
            indication = null, // Отключение эффекта затемнения
            interactionSource = remember { MutableInteractionSource() })

        {  } ) {

            Column(modifier = Modifier.padding(16.dp).verticalScroll(scroll)) {

                Row( verticalAlignment = Alignment.CenterVertically ) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer( modifier = Modifier.width(10.dp) )

                    Text("Товары и услуги", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Text("Выгрузка", fontSize = 17.sp, modifier = Modifier.fillMaxWidth()

                    .clickable( indication = null, // Отключение эффекта затемнения

                    interactionSource = remember { MutableInteractionSource() })

                { onClickDischarge() },

                    textAlign = TextAlign.Center )

                Spacer(modifier = Modifier.height(20.dp))

                Text("Характеристики", fontSize = 17.sp, modifier = Modifier.fillMaxWidth()

                    .clickable( indication = null, // Отключение эффекта затемнения

                        interactionSource = remember { MutableInteractionSource() })

                    { onClickCharacteristics() },

                    textAlign = TextAlign.Center )

                Spacer ( modifier = Modifier.height(30.dp) )

                Button (

                    onClick = {  onClickCreateOrUpdate(  ) },

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