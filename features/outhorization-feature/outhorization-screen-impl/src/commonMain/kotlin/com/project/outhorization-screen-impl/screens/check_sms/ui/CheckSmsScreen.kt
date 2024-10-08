package com.project.`outhorization-screen-impl`.screens.check_sms.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.StringRes
import com.project.`outhorization-screen-api`.UserStatus
import org.example.project.presentation.feature.authorization.screens.check_sms.viewmodel.CheckSMSEvent
import com.project.`outhorization-screen-impl`.screens.check_sms.viewmodel.CheckSMSViewModel
import com.project.`outhorization-screen-impl`.screens.check_sms.viewmodel.StatusSMS
import org.koin.mp.KoinPlatform.getKoin


class CheckSMSScreen(private val number:String,private val status: UserStatus) : Screen {
    private val viewModel = CheckSMSViewModel(getKoin().get())

    @Composable
    override fun Content(){
        CheckSMSContent(status,number,viewModel)
    }
}

@Composable
fun CheckSMSContent(status: UserStatus, number: String, viewModel: CheckSMSViewModel) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(true){
        viewModel.processIntent(CheckSMSEvent.InitDataView(status,scope))
    }
    Scaffold(
        topBar = {
            Text(
                text = StringRes.REGISTRATION_LOGIN_TITLE,
                fontSize = 35.sp,
                modifier = Modifier
                    //.align(Alignment.TopStart)
                    .padding(top = 10.dp, start = 16.dp),
                fontWeight = FontWeight.Normal,

                )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {



            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = StringRes.SMS_CODE,//stringResource(R.string.sms_code),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box {
                    SmsCodeTextField(viewModel.state.value.fullSmsCode
                    ) {
                        println(it)
                        viewModel.processIntent(CheckSMSEvent.InputSMS(it))
                    }
                    SmsCodeTextView(state.fieldSms1?:"")
                }
                Spacer(modifier = Modifier.width(12.dp))
                SmsCodeTextView(state.fieldSms2?:"")
                /*SmsCodeTextField(state.fieldSms2?:"") {
                    viewModel.processIntent(CheckSMSEvent.InputSMS(it))
                }*/
                Spacer(modifier = Modifier.width(12.dp))
                SmsCodeTextView(state.fieldSms3?:"")
                /*SmsCodeTextField(state.fieldSms3?:"") {
                    viewModel.processIntent(CheckSMSEvent.InputSMS(it))
                }*/
                Spacer(modifier = Modifier.width(12.dp))
                SmsCodeTextView(state.fieldSms4?:"")
               /* SmsCodeTextField(state.fieldSms4?:"") {
                    viewModel.processIntent(CheckSMSEvent.InputSMS(it))
                }*/

            }

            Spacer(modifier = Modifier.height(8.dp))

            if(state.statusSMS== StatusSMS.INCORRECT_SMS) {
                Text(
                    text = StringRes.INCORRECT_SMS_CODE,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 8.dp),
                    color = Color.Red,
                    style = MaterialTheme.typography.body2,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(viewModel.state.value.userStatus == UserStatus.NEW) {
                OutlinedTextField(
                    value = viewModel.state.value.name,
                    onValueChange = {viewModel.processIntent(CheckSMSEvent.InputName(it)) },
                    label = { Text(text = StringRes.NAME) },//stringResource(R.string.name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = state.secondSMSText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.processIntent(CheckSMSEvent.SendSms(number,scope))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(text = StringRes.DONE)
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(!state.isCorrectSMS){
                Text(
                    text = state.textWrong,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
fun SmsCodeTextField(char:String,onValueChange:(String)->Unit) {
    TextField(
        value = char,
        onValueChange = {
            println(it)
            onValueChange(it)
                        },
        modifier = Modifier.width(46.dp).height(55.dp).alpha(0F),
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),

        shape = RoundedCornerShape(8.dp)
    )
}
@Composable
fun SmsCodeTextView(char: String) {
   Box(
       modifier = Modifier
           .width(46.dp)
           .height(55.dp)){
       Text(
          "",// text = char,
           modifier = Modifier
               .width(46.dp)
               .height(55.dp)
               //   .padding(top = 30.dp)
               .border(
                   width = 1.dp,
                   color = Color.Black,
                   shape = RoundedCornerShape(8.dp)
               ),
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold,
           textAlign = TextAlign.Center
       )
       Text(
           text = char,
           modifier = Modifier
               .padding(top = 15.dp)
               .width(46.dp)
               .height(55.dp)
             ,
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold,
           textAlign = TextAlign.Center
       )
   }
}