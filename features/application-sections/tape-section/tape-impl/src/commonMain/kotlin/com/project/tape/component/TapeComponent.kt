package com.project.tape.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.VideoPlayer
import com.project.core_app.components.PlusButton
import com.project.core_app.components.menu_bottom_bar.ui.MenuBottomBar
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.tape.component.data_entry.ui.DataEntryTapeComponent
import com.project.tape.viewmodel.TapeIntents
import com.project.tape.viewmodel.TapeViewModel
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection

class TapeComponent( override val viewModel: TapeViewModel): NetworkComponent {

    @Composable
    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(TapeIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)){

            Column(modifier = Modifier.padding(16.dp),) {

                Text("Лента", color = Color.Black, fontSize = 20.sp)

                Spacer(modifier = Modifier.height(20.dp))

                var shouldStop by remember { mutableStateOf(false) }
                var shouldGoBackToPortraitMode by remember { mutableStateOf(false) }
                var showLoading by remember { mutableStateOf(true) }


                LazyColumn( modifier = Modifier.fillMaxHeight(0.85f) ) {

                    items(viewModel.state.listVideo) {

                        Box(
                            modifier = Modifier.fillMaxWidth().height(200.dp),

                            contentAlignment = Alignment.Center
                        ) {

                            if ( it.video != null && !viewModel.state.isVisibilityDataEntry ) {

                                VideoPlayer(
                                    modifier = Modifier,
                                    url = "https://delta.online/storage/${it.video}",
                                    isLandscape = true,
                                    shouldStop = shouldStop,
                                    onMediaReadyToPlay = { showLoading = false }
                                )
                                if (showLoading) {

                                    CircularProgressIndicator(
                                        modifier = Modifier.size(40.dp),
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {

                PlusButton {

                    viewModel.processIntents(TapeIntents.OpenCreateDataEntry(scope))

                }

                MenuBottomBar().Content(MenuBottomBarSection.TAPE)
            }

        }

        if ( viewModel.state.isVisibilityDataEntry ) {

            DataEntryTapeComponent( onClickBack = {

                viewModel.processIntents(TapeIntents.BackFromDataEntry)

            }, listProjects = viewModel.state.listProjects,

                listContragents = viewModel.state.listContragents ).Content()

        }

    }

}