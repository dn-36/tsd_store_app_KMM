package com.project.core_app.components.search_component.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.core_app.components.search_component.viewmodel.SearchIntents
import com.project.core_app.components.search_component.viewmodel.SearchViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.search

class SearchComponent (

    val onValueChange: ( text: String ) -> Unit

) {

    @Composable

    fun Content() {

        val viewModel by remember { mutableStateOf(SearchViewModel()) }

        OutlinedTextField(

            value = viewModel.state.name,

            onValueChange = { inputText ->

                onValueChange(inputText)

            viewModel.processIntents(SearchIntents.InputText(inputText))


            },

            label = { Text("Поиск по названию") },

            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp) // Стандартная высота TextField
        )

        Spacer(modifier = Modifier.height(20.dp))

    }

}