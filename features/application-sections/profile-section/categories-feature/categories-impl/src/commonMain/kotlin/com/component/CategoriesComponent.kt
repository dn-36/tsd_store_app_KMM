package com.component

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
import com.project.core_app.components.delete_component.DeleteComponent
import com.project.core_app.components.PlusButton
import com.project.core_app.components.search_component.ui.SearchComponent
import com.project.core_app.network_base_screen.NetworkComponent
import com.viewmodel.CategoriesIntents
import com.viewmodel.CategoriesViewModel
import com.component.data_entry_category.ui.DataEntryCategoryComponent
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.update_pencil

class CategoriesComponent ( override val viewModel: CategoriesViewModel) : NetworkComponent {

    @Composable
    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(CategoriesIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { if ( !viewModel.state.isVisibilityDeleteComponent ) {

                        viewModel.processIntents(CategoriesIntents.BackToProductsMenu)

                        } }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("Категории", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                SearchComponent( onValueChange = { text -> viewModel.processIntents(

                    CategoriesIntents.InputTextSearchComponent(text)) } ).Content()

                LazyColumn {

                   itemsIndexed(

                       viewModel.state.filteredListCategories ) { index, item ->


                       Box(modifier = Modifier.pointerInput(true) {

                           detectTapGestures(

                                  onPress = {
                                       if (  viewModel.state.listAlphaTools.size > index &&

                                           viewModel.state.listAlphaTools[index] == 1f ) {

                                           viewModel.processIntents(CategoriesIntents.OnePressItem)
                                       }
                                   },

                               onLongPress = {

                                   viewModel.processIntents(CategoriesIntents.LongPressItem(index))

                               },
                           )
                       }) {

                           Column(modifier = Modifier.fillMaxWidth()) {

                               Spacer(modifier = Modifier.height(10.dp))

                               Text(text = "${index + 1}.  ${item.name}", fontSize = 20.sp)

                               Spacer(modifier = Modifier.height(10.dp))

                               Box(

                                   modifier = Modifier.height(1.dp).fillMaxWidth()

                                       .background(Color.LightGray)
                               )

                           }

                           if ( viewModel.state.listAlphaTools.size > index &&

                               viewModel.state.listAlphaTools[index] == 1f

                           ) {

                               Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                                   Image(painter = painterResource(Res.drawable.update_pencil),
                                       contentDescription = null,
                                       modifier = Modifier.size(17.dp)
                                           .clickable(
                                               indication = null, // Отключение эффекта затемнения
                                               interactionSource = remember { MutableInteractionSource() })

                                           {
                                               if (!viewModel.state.isVisibilityDeleteComponent) {

                                                   viewModel.processIntents(

                                                       CategoriesIntents.OpenUpdateDataEntry(item)
                                                   )
                                               }
                                           })


                                   Spacer(modifier = Modifier.width(20.dp))

                                   Image(painter = painterResource(Res.drawable.cancel),
                                       contentDescription = null,
                                       modifier = Modifier.size(15.dp)
                                           .clickable(
                                               indication = null, // Отключение эффекта затемнения
                                               interactionSource = remember { MutableInteractionSource() })

                                           {
                                               viewModel.processIntents(

                                                   CategoriesIntents.OpenDeleteComponent(item)
                                               )
                                           })

                               }
                           }

                       }

                       Spacer(modifier = Modifier.height(10.dp))

                    }

                }

            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {

                PlusButton {

                    if ( !viewModel.state.isVisibilityDeleteComponent ) {

                        viewModel.processIntents(CategoriesIntents.OpenCreateDataEntry)

                    }

                }

            }

        }

        if ( viewModel.state.isVisibilityDataEntryComponent ) {

            DataEntryCategoryComponent(

                item = viewModel.state.updateItem,

                onClickBack = {

                viewModel.processIntents(CategoriesIntents.BackFromDataEntry)

            }, onClickCreate = { name -> viewModel.processIntents(

                CategoriesIntents.CreateCategory(scope, name )) },

                onClickUpdate = { name -> viewModel.processIntents(

                    CategoriesIntents.UpdateCategory( scope, name)) }).Content()

        }

        else if ( viewModel.state.isVisibilityDeleteComponent ) {

            DeleteComponent( name = "категорию",

                onClickNo = { viewModel.processIntents(CategoriesIntents.NoDelete) },

                onClickDelete = { viewModel.processIntents(
                    CategoriesIntents.DeleteCategory(

                    scope )) }).Content()

        }

    }
}