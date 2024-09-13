package org.example.project.presentation.feature.authorization.screens.entering_number.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.feature.authorization.screens.entering_number.component.LoadingComponent
import org.example.project.presentation.core.NavigatorView
import org.tsdstore.project.feature.authorization.presentation.screens.entering_number.component.TogiCountryCodePicker
import org.tsdstore.project.feature.authorization.presentation.screens.entering_number.viewmodel.EnteringNumberEvent
import org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel.EnteringNumberViewModel
import org.koin.mp.KoinPlatform.getKoin

object EnteringAnumberScreen : Screen {

    private val viewModel = EnteringNumberViewModel(getKoin().get())

@Composable
override fun Content() {
       val state by viewModel.state.collectAsState()
       val scope  = rememberCoroutineScope()
        NavigatorView.navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {

            Text(
                "Авторизация",
                fontSize = 35.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 10.dp, start = 16.dp),
                fontWeight = FontWeight.Normal,

                )


            Column(Modifier.align(Alignment.Center)) {
                Text(
                    "Укажите номер",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(end = 250.dp),
                    fontWeight = FontWeight.Bold,

                    )
                TogiCountryCodePicker(
                    countryData = state.countryData,
                    text = state.number,
                    onValueChange = { viewModel.processIntent(EnteringNumberEvent.InputNumber(it)) },
                    unfocusedBorderColor = MaterialTheme.colors.primary,
                    bottomStyle = false,
                    shape = RoundedCornerShape(24.dp),
                    isOpenDialog = state.isOpenSelectionCodeCountryDialog,
                    isCorrectStatusDiolog = state.isCorrectNumberStatus,
                    onCloseDialog = { viewModel.processIntent(EnteringNumberEvent.CloseDialog) },
                    onClickCountryFlag = {
                        viewModel.processIntent(
                            EnteringNumberEvent
                                .OpenSelectionCodeCountryDialog
                        )
                    },
                    selectCountry = { countryData ->
                        viewModel.processIntent(EnteringNumberEvent.SelectCountry(countryData))
                    }
                )

                Text(
                    "продолжая, вы соглашаетесь со сбором и обработкой " +
                            "персональных данных и рользовательских соглошений",
                    //  fontFamily = fontFamaly.getFont(0),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(15.dp),
                    fontWeight = FontWeight.Normal,
                )
            }
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                onClick = {
                    viewModel.processIntent(
                        EnteringNumberEvent.SendNumber(
                        state.countryData.countryPhoneCode + state.number,
                            scope
                    ))
                }) {
                Text(text = "Продолжить", fontSize = 20.sp)
            }
           if(viewModel.state.value.isLoadingScreen) {
               LoadingComponent()
           }
        }

    }
}





