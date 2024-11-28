package org.example.project.presentation.feature.authorization.screens.entering_number.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.project.core_app.components.menu_bottom_bar.model.CountryData
import com.project.`outhorization-screen-impl`.screens.entering_number.component.TogiCodeDialog


//private var fullNumberState: String by mutableStateOf("")
//private var checkNumberState: Boolean by mutableStateOf(true)
//private var phoneNumberState: String by mutableStateOf("")
//private var countryCodeState: String by mutableStateOf("")


@Composable
fun TogiCountryCodePicker(
    modifier: Modifier = Modifier,
    text: String,
    countryData: CountryData,
    onValueChange: (String) -> Unit,
    shape: Shape = RoundedCornerShape(24.dp),
    color: Color = MaterialTheme.colors.background,
    showCountryCode: Boolean = true,
    showCountryFlag: Boolean = true,
    focusedBorderColor: Color = MaterialTheme.colors.primary,
    unfocusedBorderColor: Color = MaterialTheme.colors.onSecondary,
    cursorColor: Color = MaterialTheme.colors.primary,
    bottomStyle: Boolean = false,
    isOpenDialog:Boolean,
    isCorrectStatusDiolog:Boolean,
    onClickCountryFlag:()->Unit,
    onCloseDialog:()->Unit,
    selectCountry: (CountryData) -> Unit,
) {
    val keyboardController = LocalTextInputService.current


    Surface(color = color) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            if (bottomStyle) {
                TogiCodeDialog(
                    countryData = countryData,
                    showCountryCode = showCountryCode,
                    showFlag = showCountryFlag,
                    isOpenDialog = isOpenDialog,
                    onClickCountryFlag = onClickCountryFlag,
                    onClickDismiss = onCloseDialog,
                    pickedCountry = selectCountry
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(modifier = modifier.fillMaxWidth(),
                    shape = shape,
                    value = text,
                    onValueChange = {
                        //textFieldValue = it
                        if (text != it) {
                            onValueChange(it)
                        }
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isCorrectStatusDiolog) focusedBorderColor else Color.Red,
                        unfocusedBorderColor = if (isCorrectStatusDiolog) unfocusedBorderColor else Color.Red,
                        cursorColor = cursorColor
                    ),
                   //visualTransformation = PhoneNumberTransformation(getLibCountries.single { it.countryCode == defaultLang }.countryCode.uppercase()),
                    placeholder = {
                        Text(text = countryData.numberHint)},
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword,
                       // autoCorrect = true,
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hideSoftwareKeyboard()
                    }),
                    leadingIcon = {
                        if (!bottomStyle)
                            Row {
                                Column {
                                    TogiCodeDialog(
                                       /* pickedCountry = {
                                            phoneCode = it.countryPhoneCode
                                            defaultLang = it.countryCode
                                        },*/
                                        countryData = countryData,
                                        //defaultSelectedCountry = ConstData.LIB_COUNTRIES.single { it.countryCode == defaultLang },
                                        showCountryCode = showCountryCode,
                                        showFlag = showCountryFlag,
                                        isOpenDialog = isOpenDialog,
                                        onClickCountryFlag = onClickCountryFlag,
                                        onClickDismiss = onCloseDialog,
                                        pickedCountry = selectCountry
                                    )
                                }
                            }
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                           //  textFieldValue = ""
                            onValueChange("")
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Clear",
                                tint = if (isCorrectStatusDiolog)
                                    MaterialTheme.colors.onSurface
                                else
                                    Color.Red
                            )
                        }
                    })
            }
            if (!isCorrectStatusDiolog)
                Text(
                text = "Некоректный номер",
                color = MaterialTheme.colors.error,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, top = 15.dp)

            )
        }
    }


}