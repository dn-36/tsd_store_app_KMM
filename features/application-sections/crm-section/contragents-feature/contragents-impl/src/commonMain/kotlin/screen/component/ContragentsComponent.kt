package screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.components.delete_component.DeleteComponent
import com.project.core_app.components.search_component.ui.SearchComponent
import com.project.core_app.network_base_screen.NetworkComponent
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.plus
import project.core.resources.update_pencil
import screen.component.create_and_update_component.ui.CreateAndUpdateComponent
import screen.viewmodel.ContragentsIntents
import screen.viewmodel.ContragentsViewModel

class ContragentsComponent ( override val viewModel: ContragentsViewModel) : NetworkComponent {

    @Composable

    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(ContragentsIntents.GetContragents(scope))

        Box( modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { if (

                            viewModel.state.isVisibleCreateAndUpdateComponent == 0f

                            && viewModel.state.isVisibleDeleteComponent == 0f

                        ) { viewModel.processIntents(ContragentsIntents.

                        Back ) } }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                Text("Контрагенты", color = Color.Black, fontSize = 20.sp)

            }

                Spacer(modifier = Modifier.height(20.dp))

                SearchComponent( onValueChange = { text -> viewModel.processIntents(

                    ContragentsIntents.InputTextSearchComponent(text)) } ).Content()

                LazyColumn ( ) {

                    itemsIndexed(viewModel.state.listFilteredContragents) { index, it ->

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                        ) {

                            Column {

                                Text(
                                    text = "${index+1}.  ${it.name ?: 0}", fontSize = 20.sp,

                                    modifier = Modifier.fillMaxWidth(0.8f)
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                it.entits!!.forEach {entity ->

                                    Text(
                                        text = "Юр.лицо:  ${entity.name?: entity.ui}", fontSize = 16.sp,

                                        color = Color.Gray,

                                        modifier = Modifier.fillMaxWidth(0.8f)
                                    )

                                    Spacer(modifier = Modifier.height(5.dp))

                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Box(
                                    modifier = Modifier.height(1.dp).fillMaxWidth()
                                        .background(Color.LightGray)
                                )

                            }

                            Row(modifier = Modifier.align(Alignment.TopEnd)) {

                                Image(painter = painterResource(Res.drawable.update_pencil),
                                    contentDescription = null,
                                    modifier = Modifier.size(17.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        { if (

                                            viewModel.state.isVisibleCreateAndUpdateComponent == 0f

                                            && viewModel.state.isVisibleDeleteComponent == 0f

                                            ) { viewModel.processIntents(ContragentsIntents.

                                        OpenCreateOrUpdateComponent ( it, 1 )) }})


                                Spacer(modifier = Modifier.width(20.dp))

                                Image(painter = painterResource(Res.drawable.cancel),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        { if (

                                            viewModel.state.isVisibleCreateAndUpdateComponent == 0f

                                            && viewModel.state.isVisibleDeleteComponent == 0f

                                        ) {
                                            viewModel.processIntents(

                                                ContragentsIntents.OpenDeleteComponent(it)
                                            )
                                        }

                                        })

                            }


                        }

                    }

                }

            }

            Box( modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
                .size(60.dp).clip(CircleShape).background(Color.White).border(

                    width = 2.dp, color = Color.Black, shape = CircleShape

                )
                , contentAlignment = Alignment.Center) {
                Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                    modifier = Modifier.size(60.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        {
                            if (

                                viewModel.state.isVisibleCreateAndUpdateComponent == 0f

                                && viewModel.state.isVisibleDeleteComponent == 0f

                            ) {

                            viewModel.processIntents(ContragentsIntents.

                            OpenCreateOrUpdateComponent ( null, 2 )) }})
            }
        }

        if ( viewModel.state.isVisibleCreateAndUpdateComponent == 1f ) {

            CreateAndUpdateComponent( viewModel.state.index, viewModel.state.updatedItem,

                onClickCreate = { name, scope ->

                    viewModel.processIntents(ContragentsIntents.Create( name = name, coroutineScope = scope ))

                }, onClickUpdate = { name, scope -> viewModel.processIntents(

                    ContragentsIntents.Update( name = name, coroutineScope = scope )) },

                onClickCansel = { viewModel.processIntents(ContragentsIntents.CanselComponent) }).Content()

        }

        else if ( viewModel.state.isVisibleDeleteComponent == 1f ) {

            DeleteComponent(

                name = "контрагента",

                onClickDelete = { viewModel.processIntents(

                ContragentsIntents.DeleteContragent ( scope)) }, onClickNo = {

                    viewModel.processIntents(ContragentsIntents.NoDelete)

            } ).Content()

        }


    }

}