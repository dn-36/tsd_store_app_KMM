package com.project.chats.screens.history_files.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.ViewModel
import com.project.chats.screens.dialog.components.DialogComponentScreen
import com.project.chats.screens.gallery.GalleryMediaScreen
import com.project.chats.screens.history_files.viewmodel.models.HistoryFilesState
import com.project.chats.screens.history_files.viewmodel.models.Photo
import com.project.network.Navigation
import org.example.project.nika_screens_chats.history_files_feature.viewmodel.HistoryFilesIntents

class HistoryFilesViewModel(
    private val listPhoto:List<Photo>,
    private val dialog:DialogComponentScreen
) : ViewModel() {
    private var uiMessage:String = ""
    val state = mutableStateOf(
        HistoryFilesState()
    )

    init{
        state.value = state.value.copy(listPhoto)
    }

    fun processIntent(intent: HistoryFilesIntents) {
        when (intent) {
            is HistoryFilesIntents.Back -> {
                back()
            }
        }
    }

    fun back() {
        Navigation.navigator.pop()
    }

    fun showGallery(listPhotos:List<String>,selectedImage:String){

        listPhotos.forEachIndexed { index, url ->
            if(selectedImage == url){
                Navigation.navigator.push(GalleryMediaScreen(listPhotos,index))
            }
        }
    }



        fun selectPhoto(photo: Photo){
            val list = state.value.listPhoto.toMutableList()
            state.value.listPhoto.fastForEachIndexed { index, urlPhoto ->
                if(photo == urlPhoto) {
                    list[index] =  urlPhoto.copy(isSelected = true)
                    uiMessage = list[index].uiMessage
                }else{
                    list[index] =  urlPhoto.copy(isSelected = false)
                }
            }

            state.value = state.value.copy(
                list,
                isVisibilityButtonGoToPlacePhoto = true
            )
        }

        fun goToPlacePhoto(){
            dialog.viewModel.state = dialog.viewModel.state.copy(
               uiSelectedPhoto =  uiMessage
            )
            Navigation.navigator.pop()
        }


    }

