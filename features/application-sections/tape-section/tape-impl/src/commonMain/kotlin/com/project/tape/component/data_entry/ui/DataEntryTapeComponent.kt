package com.project.tape.component.data_entry.ui

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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.tape.component.data_entry.viewmodel.DataEntryTapeIntents
import com.project.tape.component.data_entry.viewmodel.DataEntryTapeViewModel
import com.project.tape.model.ProjectModel
import com.project.tape.model.ProjectResponseModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back

class DataEntryTapeComponent (

    val onClickBack:() -> Unit,

    val listProjects: List<ProjectResponseModel>

    ) {

    val viewModel = DataEntryTapeViewModel()

    @Composable

    fun Content(){

        viewModel.processIntents(DataEntryTapeIntents.SetScreen(listProjects))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp),) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("Лента", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = viewModel.state.name,

                    onValueChange = {

                    viewModel.processIntents(DataEntryTapeIntents.InputTextName(it))

                    },
                    label = { Text("Название") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp) // Стандартная высота TextField
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.description,

                    onValueChange = {

                    viewModel.processIntents(DataEntryTapeIntents.InputTextDescription(it))

                    },
                    label = { Text("Описание") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp) // Стандартная высота TextField
                )

            }
        }
    }
}