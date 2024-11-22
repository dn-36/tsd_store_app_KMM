package screen.component.create_and_update_component.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.cancel
import screen.component.create_and_update_component.viewmodel.CreateAndUpdateIntents
import screen.component.create_and_update_component.viewmodel.CreateAndUpdateViewModel
import screen.model.ContragentResponseModel

class CreateAndUpdateComponent (

    val index: Int,

    val item: ContragentResponseModel?,

    val onClickCreate: ( name:String ) -> Unit,

    val onClickUpdate: ( name:String ) -> Unit,

    val onClickCansel: () -> Unit

) {

    val vm = CreateAndUpdateViewModel()

    @Composable
     fun Content() {

         vm.processIntents(CreateAndUpdateIntents.SetScreen(item))

        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0.6f)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
            ) {

                Column(modifier = Modifier.padding(16.dp).fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {

                    Row ( modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.End ) {

                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                { onClickCansel() })

                    }

                        OutlinedTextField(

                            value = vm.state.name,

                            onValueChange = {

                                vm.processIntents(CreateAndUpdateIntents.InputText(it))
                            },
                            label = { Text("Название") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp) // Стандартная высота TextField
                        )


                    Spacer(modifier = Modifier.height(8.dp))

                    if ( index == 2 ) {

                        Button(
                            onClick = {

                                      onClickCreate( vm.state.name )

                            },
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Создать")
                        }

                    }

                    else if ( index == 1 ) {

                        Button(
                            onClick = { onClickUpdate( vm.state.name ) },
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Редактировать")
                        }

                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.03f)
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ){

            }

        }
    }

}