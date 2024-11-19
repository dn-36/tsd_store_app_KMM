package com.project.menu_crm_api.categories.component.data_entry_category.ui

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
import com.project.`menu-crm-api`.categories.model.CategoryResponseModel
import com.project.menu_crm_api.categories.component.data_entry_category.viewmodel.DataEntryCategoryIntents
import com.project.menu_crm_api.categories.component.data_entry_category.viewmodel.DataEntryCategoryViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back

class DataEntryCategoryComponent(

    val item: CategoryResponseModel?,

    val onClickBack:() -> Unit,

    val onClickCreate:( name:String ) -> Unit,

    val onClickUpdate:( name: String ) -> Unit

) {

    val viewModel = DataEntryCategoryViewModel()

    @Composable
    fun Content() {

        viewModel.processIntents(DataEntryCategoryIntents.SetScreen(item?.name?:""))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

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


                    Text("Категории", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = viewModel.state.name,

                    onValueChange = {

                    viewModel.processIntents(DataEntryCategoryIntents.InputTextName(it))

                    },

                    label = { Text("Название", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(30.dp))

                if ( item == null ) {


                    Button(

                        onClick = {

                            onClickCreate(viewModel.state.name)

                        },

                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth()

                    ) {

                        Text(text = "Создать")

                    }

                }

                else {

                    Button(

                        onClick = {

                            onClickUpdate(viewModel.state.name)

                        },

                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth()

                    ) {

                        Text(text = "Редактировать")

                    }

                }
            }
        }
    }
}