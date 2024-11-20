package com.project.chats.screens.dialog.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.util.fastForEachIndexed
import com.project.chats.screens.chats.screen.ChatsScreen
import com.project.chats.screens.dialog.components.DialogComponentScreen
import com.project.chats.screens.dialog.domain.GetListMessagesUseCase
import com.project.chats.screens.dialog.domain.ReadMessegeUseCase
import com.project.chats.screens.dialog.domain.SendMessageUseCase
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.ReplyMessage
import com.project.chats.screens.dialog.domain.models.StatusMessage
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.project.chats.screens.gallery.GalleryMediaScreen
import com.project.core_app.getFormattedDateTime
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.core_app.utils.imageBitmapToBase64
import com.project.network.Navigation
import com.project.network.chats_network.ChatsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.project.chats.screens.history_files.screen.HistoryFilesScreen
import com.project.chats.screens.history_files.viewmodel.models.Photo
import kotlin.coroutines.cancellation.CancellationException

class DialogViewModel(
    private val getListMessagesUseCase: GetListMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val readMessageUseCase: ReadMessegeUseCase
) : NetworkViewModel() {
    private var isSeted: Boolean = false
    var state by mutableStateOf(DialogState())
    private var uiChats: String = ""
    private lateinit var scope: CoroutineScope
    private var jobUpdate: Job? = null
    private var selectedUi:String = ""
    private var selectedMessage:List<String>? = null
    private val DELETE_FOR_ALL = 1
    private val DELETE_FOR_SOMESELVE = 0

    fun processIntent(intent: DialogIntents) {
        when (intent) {
            is DialogIntents.Back -> back()
            is DialogIntents.HistoryFiles -> historyFiles(intent.dialogComponentScreen)
            is DialogIntents.SetScreen -> {
                if (isSeted) return
                this@DialogViewModel.uiChats = intent.uiChats
                this@DialogViewModel.scope = intent.scope
                setScreen()
            }
            is DialogIntents.OpenSelectFileComponent -> { openSelectFileComponent() }

            is DialogIntents.CloseSelectFileComponent -> { closeSelectFileComponent() }

            is DialogIntents.AddNewPhotosGalleryOrCamera -> { addNewPhotosGalleryOrCamera( intent.images ) }

            is DialogIntents.DeleteSelectedPhoto -> { deleteSelectedPhoto( intent.image ) }
        }
    }

    private fun back() {
        Navigation.navigator.push(ChatsScreen())
    }

    private fun historyFiles(dialog:DialogComponentScreen) {
     val listUrlImage = mutableListOf<Photo>()
         state.listMessage.forEach{
             if(it.url_icon != null){
            listUrlImage.add(Photo(it.url_icon!!,it.ui))
            }
        }
       // println("QQQQIIII++++OOO")
        println(listUrlImage)
      //  println("QQQQIIII++++OOO")
        Navigation.navigator.push(HistoryFilesScreen(
          listUrlImage,dialog
        ))

    }

    private fun setScreen() {
        try {
            jobUpdate?.cancel() // Отменяем предыдущее обновление, если есть
            jobUpdate = updateDate()
        } catch (e: Exception) {
            println("Error in setScreen: $e")
            setStatusNetworkScreen(StatusNetworkScreen.ERROR)
        }
    }

    private fun updateDate(): Job = scope.launch(Dispatchers.IO) {
        try {
            while (isActive) {

                readMessageUseCase.execute(uiChats)
                val listDate = mutableSetOf<String>()
                val listMessages = getListMessagesUseCase.execute(uiChats) ?: emptyList()

                listMessages.fastForEachIndexed { i, message ->
                    if(state.isShowSelectMessage){

                        selectedMessage?.map {
                            listMessages[i].isShowSelectedMessage = true
                            listMessages[i].isSelectedMessage = if(!listMessages[i].isSelectedMessage)
                                message.ui == it else true
                        }


                    }

                    if (message.isReaded) {
                        listMessages[i].statusMessage = StatusMessage.IS_READED
                    }

                    if (!listDate.contains(listMessages[i].time)) {
                        listMessages[i].isShowDate = true
                    }

                    listDate.add(message.time)
                }

                if (
                    listMessages.isNotEmpty() &&
                    (state.listMessage.lastOrNull()?.statusMessage ?: StatusMessage.IS_SECCUESS)
                    != StatusMessage.IS_ERROR
                ) {
                    state = state.copy(
                        listMessage = listMessages
                    )
                }

                if (!isSeted) {
                    delay(200L)
                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)
                    isSeted = true
                }

                delay(1500L)
            }
        } catch (e: CancellationException) {
            println("Update job cancelled")
        } catch (e: Exception) {
            println("Error in updateDate: $e")
        }
    }

    fun sendMessage(text: String, ui: String, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            val list = state.listMessage.toMutableList()

            list.add(
                Message(
                    text,
                    "You",
                    date = getFormattedDateTime().date,
                    time = getFormattedDateTime().time,
                    WhoseMessage.YOU,
                    false,
                    answerMessage = state.replyMessage,
                    ui = ui,
                    statusMessage = StatusMessage.IS_LOADING,
                    sendImage = if (state.listImages.isNotEmpty()) state.listImages.first() else null
                )
            )

            state = state.copy(
                listMessage = list,
                uiSelectedPhoto = "",
                )

            val result = sendMessageUseCase.execute(
                text,
                state.replyMessage?.ui ?: "",
                if (state.listImages.isNotEmpty())
                    imageBitmapToBase64(state.listImages.first())
                else
                    null,
                ui
            )
            state = state.copy(
            listImages = listOf()
            )
             println("LLLLLLLL____LLLLLLLLLL_____LLLLLLLL_____LLLLLLLLLL")
            if (result != sendMessageUseCase.ERROR) {
                val removeIndex = mutableListOf<Int>()
                val updatedList = list.mapIndexed { index, message ->
                    if (message.statusMessage == StatusMessage.IS_ERROR) {
                        removeIndex.add(index)
                    }
                    if (index == list.size - 1) {
                        message.copy(statusMessage = StatusMessage.IS_SECCUESS)
                    } else {
                        message
                    }
                }.toMutableList()

                removeIndex.sortedDescending().forEach {
                    updatedList.removeAt(it)
                }

                state = state.copy(listMessage = updatedList)
            } else {
                val updatedList = list.mapIndexed { index, message ->
                    if (index == list.size - 1) {
                        message.copy(statusMessage = StatusMessage.IS_ERROR)
                    } else {
                        message
                    }
                }
                state = state.copy(
                    listMessage = updatedList,
                    replyMessage = null,
                    listImages = listOf()
                )
            }
        }
    }



    fun showSelectMessage(ui: String) {
        // Обновление selectedMessage
        selectedUi = ui
        // jobUpdate?.cancel()
        val _listSelectedMessage = selectedMessage?.toMutableList() ?: mutableListOf()
        if (_listSelectedMessage.contains(ui)) {
            _listSelectedMessage.remove(ui)
        } else {
            _listSelectedMessage.add(ui)
        }
        selectedMessage = _listSelectedMessage

        if (_listSelectedMessage.size == 0) {
            val updatedList = state.listMessage.map { message ->
                    message.copy(
                        isSelectedMessage = false,
                        isShowSelectedMessage  = false
                    )
            }
                    state = state.copy(
                        isShowSelectMessage = false,
                        listMessage = updatedList
                    )
        } else {


            val updatedList = state.listMessage.map { message ->
                if (message.ui == ui) {
                    message.copy(
                        isShowSelectedMessage = true,
                        isSelectedMessage = !message.isSelectedMessage
                    )
                } else {
                    message.copy(
                        isShowSelectedMessage = true
                    )
                }
            }

            state = state.copy(
                isShowSelectMessage = true,
                listMessage = updatedList
            )
        }
    }

    fun showDeleteMessage() {
        state = state.copy(isShowDeleteDialog = true)
    }

    fun onClickDeleteDialogAgree(scope:CoroutineScope) {
        var list = state.listMessage.toMutableList()
        var minusSize = 0
        state.listMessage.forEachIndexed { indexMessage, message ->
            selectedMessage?.forEach { ui ->
                if (message.ui == ui) {
                    list.removeAt(indexMessage-minusSize)
                    ++minusSize
                }
            }
        }

        list = list.map {
            it.copy(
                isShowSelectedMessage = false,
                isSelectedMessage = false
            )
        }.toMutableList()

        state = state.copy(
            isShowSelectMessage = false,
            isShowDeleteDialog = false,
            listMessage = list
        )


        scope.launch {
         jobUpdate?.cancel()
         //println("println(ChatsApi().deleteMessage(selectedMessage!!,DELETE_FOR_ALL))")
         //println(
             ChatsApi().deleteMessage(selectedMessage!!,DELETE_FOR_ALL)
         //)
         //println("println(ChatsApi().deleteMessage(selectedMessage!!,DELETE_FOR_ALL))")
         jobUpdate = updateDate()
        }

    }

    fun onClickDeleteDialogCancel() {
        state = state.copy(
            isShowSelectMessage = false,
            isShowDeleteDialog = false
        )
    }
    fun cancelReplyMessage(){
        state = state.copy(replyMessage = null)
    }
    fun selectReplyMessage(replyMessage: ReplyMessage){

        state = state.copy(
            replyMessage = replyMessage,
        )
    }

    fun showPhoto(list: List<String>){
        Navigation.navigator.push( GalleryMediaScreen(list))
    }

    private fun openSelectFileComponent () {

        state = state.copy(

            isVisibilitySelectFileComponent = true

        )


    }

    private fun closeSelectFileComponent () {

        state = state.copy(

            isVisibilitySelectFileComponent = false

        )


    }

    private fun addNewPhotosGalleryOrCamera ( images: List<ImageBitmap> ) {

        val newList = state.listImages.toMutableList()

        newList.addAll(images)

        state = state.copy(
            isVisibilitySelectFileComponent = false,
            listImages = newList
        )


    }

    private fun deleteSelectedPhoto ( image: ImageBitmap) {

        val newList = state.listImages.toMutableList()

        newList.remove(image)

        state = state.copy(

            listImages = newList

        )
    }
        @Composable
    fun setPositionChat(
        uiChat:String,
        actionScrollable: @Composable (index:Int)->Unit
    ){
        state.listMessage.forEachIndexed { index, message ->
            if(uiChat == message.ui)
            actionScrollable(index)
        }
    }

    }
