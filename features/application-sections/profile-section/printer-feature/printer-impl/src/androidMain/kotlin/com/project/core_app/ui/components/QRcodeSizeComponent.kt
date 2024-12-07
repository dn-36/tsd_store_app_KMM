package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.viewmodel.model.CategoryPrinter
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.img

object QRcodeSizeComponent {
    @Composable
    fun Content(
        qrCode:Bitmap,
        title:Bitmap,
        heightQrCode: Float,
        fontSize:Float,
        widthBareCode:Float,
        category: CategoryPrinter,
        actionChangeFontSize:(Float)->Unit,
        actionChangeHeightQRcode:(Float)->Unit,
        actionChangeWidthBareCode:(Float)->Unit,
        actionSavedSettings:()->Unit,
        actionCloseSettings:()->Unit,

        ) {

        var isClicked =  remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0.6f)
                  //  .clickable { actionCloseSettings() }
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxHeight(0.85f+heightQrCode/300)
                    .fillMaxWidth()
                    .align(BottomCenter)
                    .background(Color.White)
            ) {
               Image(
                   painterResource(Res.drawable.img),
                   contentDescription = null,
                   modifier = Modifier
                       .padding(end = 20.dp,top = 20.dp)
                       .size(30.dp)
                       .align(Alignment.TopEnd)
                       .clickable {
                           actionCloseSettings()
                       }
                   )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text("Настройки печати:", color = Color.Black, fontSize = 20.sp)

                    Spacer(modifier = Modifier.height(30.dp))
                    Image(
                        bitmap = qrCode.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .height((heightQrCode * 3.5F).dp)
                            .fillMaxWidth()
                            //.width(150.dp)
                    )

                    //Spacer(modifier = Modifier.height(32.dp))
                    Spacer(modifier = Modifier.height(10.dp))


                    Image(
                        bitmap = title.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .height(130.dp)
                            .width(250.dp)
                    )

                    Spacer(modifier = Modifier.height(25.dp))



                        SettingsSliderComponent(
                            "Bысота QR code",
                            10f..50f,
                            heightQrCode,
                            {actionChangeHeightQRcode(it)}
                        )

                    Spacer(modifier = Modifier.height(22.dp))
                        SettingsSliderComponent(
                            "Размер шрифта",
                            5f..10f,
                            fontSize,
                            {actionChangeFontSize(it)}
                        )
                    Spacer(modifier = Modifier.height(22.dp))
                   SettingsSliderComponent(
                        "Щирина bar code",
                        //1f..4f,
                        1F..4F,
                        widthBareCode.toInt().toFloat()
                    ) {
                        actionChangeWidthBareCode(it)
                    }



                    Spacer(modifier = Modifier.height(22.dp))

                        Button(
                            onClick = {
                                isClicked.value = true
                                actionSavedSettings()
                                      },
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.95f)
                        ) {
                            Text(text = if(category == CategoryPrinter.VKP) "Сохранить" else "Далее")
                        }


                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.03f)
                    .background(Color.White)
                    .align(BottomCenter)
            )

        }
    }
}

@Composable
private fun SettingsSliderComponent(
    title: String,
    range:ClosedFloatingPointRange<Float>,
    count: Float,
    actionChange:(Float)->Unit  ) {
    val countSlider = remember {
        mutableStateOf(0F)
    }

    Column {

        Text(title, color = Color.Black, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${count.toInt()}", color = Color.Black, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(10.dp))

            Slider(
                value = countSlider.value,
                onValueChange = {
                    countSlider.value = it
                    actionChange(it)
                                },
                valueRange = range,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}

