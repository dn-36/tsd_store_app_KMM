package com.project.chats.screens.history_files.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.screens.dialog.components.DialogComponentScreen
import com.skydoves.landscapist.coil3.CoilImage
import org.example.project.nika_screens_chats.history_files_feature.viewmodel.HistoryFilesIntents
import com.project.chats.screens.history_files.viewmodel.HistoryFilesViewModel
import com.project.chats.screens.history_files.viewmodel.models.Photo
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.done
import project.core.resources.eye


class HistoryFilesScreen(private val listUrlImage:List<Photo>?, dialogScreen:DialogComponentScreen) : Screen {

    private val vm = HistoryFilesViewModel(
        listUrlImage?: listOf(),
        dialogScreen
    )

    @Composable
    override fun Content() {

        var alphaMedia by remember { mutableStateOf(1f) }

        var alphaFiles by remember { mutableStateOf(0f) }

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            if(vm.state.value.isVisibilityButtonGoToPlacePhoto) {
                Image(
                    painter = painterResource(resource = Res.drawable.eye),
                    modifier = Modifier
                        .padding(16.dp)
                        .size(20.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            vm.goToPlacePhoto()
                        },
                    contentDescription = null
                )
            }
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(0.6f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(Res.drawable.back),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() })
                        { vm.processIntent(HistoryFilesIntents.Back) }
                    )

                    Text("История файлов", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        {
                            alphaMedia = 1f
                            alphaFiles = 0f
                        }) {

                        Text("Медиа", fontSize = 15.sp)

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier.alpha(alphaMedia).width(70.dp).height(1.dp)
                                .background(Color.Black)
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        {
                            alphaMedia = 0f
                            alphaFiles = 1f
                        }) {
                        Text("Файлы", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.alpha(alphaFiles).width(70.dp).height(1.dp)
                                .background(Color.Black)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn {
                    itemsIndexed(vm.state.value.listPhoto.chunked(3)) { indexes, item ->
                        Row(
                            modifier = Modifier
                                .padding(start = 2.dp, end = 2.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            item.forEachIndexed { index, image ->
                               Box(  modifier = Modifier//.padding(5.dp)
                                   .border(1.dp,Color.Black)
                                    .weight(0.5F)
                                   .aspectRatio(1f)
                                  // .clickable { vm.showGallery(listUrlImage!!.map { it.urlPhoto },image.urlPhoto) }) {

                                   .pointerInput(Unit) {
                                   detectTapGestures(

                                       onTap = {
                                           vm.showGallery(listUrlImage!!.map { it.urlPhoto },image.urlPhoto)
                                       },

                                       onLongPress = {

                                           vm.selectPhoto(image)

                                       },
                                   )
                               }
                               ){
                                   CoilImage(
                                       imageModel = {
                                           image.urlPhoto
                                                    },
                                   modifier = Modifier
                                       .alpha(if(image.isSelected) 0.5f else 1F )
                                       .aspectRatio(1f)
                                   )
                                   if(image.isSelected) {
                                       Image(
                                           painter = painterResource(Res.drawable.done),
                                           contentDescription = null,
                                           modifier = Modifier.size(50.dp).align(Alignment.Center)
                                       )
                                   }
                               }


                            }

                        }
                    }
                }
            }
        }
    }
}