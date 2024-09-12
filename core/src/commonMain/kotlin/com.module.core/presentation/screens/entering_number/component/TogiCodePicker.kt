package org.tsdstore.project.feature.authorization.presentation.screens.entering_number.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.module.core.presentation.screens.entering_number.model.CountryData
import org.tsdstore.project.feature.authorization.presentation.ConstData
import org.tsdstore.project.feature.authorization.presentation.utils.searchCountry
import org.tsdstore.project.value.StringRes
import org.jetbrains.compose.resources.painterResource


@Composable
fun TogiCodeDialog(
    modifier: Modifier = Modifier,
    padding: Dp = 15.dp,
    showCountryCode: Boolean = true,
    countryData: CountryData,
    showFlag: Boolean = true,
    showCountryName: Boolean = false,
    isOpenDialog:Boolean,
    onClickCountryFlag:()->Unit,
    onClickDismiss:()->Unit,
    pickedCountry: (CountryData) -> Unit,

    ) {

    val countryList: List<CountryData> = ConstData.LIB_COUNTRIES
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier
        .padding(padding)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
        ) {
           // isOpenDialog = true
            onClickCountryFlag()
        }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showFlag) {
               /* Image(
                    modifier = modifier.width(34.dp),
                    painter = painterResource(
                        resource = countryData.flagDrawableResource
                    ), contentDescription = null
                )*/
            }
            if (showCountryCode) {
                Text(
                    text = countryData.countryPhoneCode,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 6.dp),
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
            if (showCountryName) {
                Text(
                    text = ConstData.LIB_COUNTRIES.single {
                        it.countryCode ==  countryData.countryCode.lowercase()
                    }.countryName, //getCountryName(isPickCountry.countryCode.lowercase()),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 6.dp),
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }


        if (isOpenDialog) {
            CountryDialog(
                countryList = countryList,
                onDismissRequest = { onClickDismiss() },
                dialogStatus = isOpenDialog,
                onSelected = { countryItem ->
                    pickedCountry(countryItem)
                    onClickDismiss()
                }
            )
        }
    }
}

@Composable
fun CountryDialog(
    modifier: Modifier = Modifier,
    countryList: List<CountryData>,
    onDismissRequest: () -> Unit,
    onSelected: (item: CountryData) -> Unit,
    dialogStatus: Boolean,
) {
    var searchValue by remember { mutableStateOf("") }
    if (!dialogStatus) searchValue = ""

    Dialog(
        onDismissRequest = onDismissRequest,
        content = {
            Surface(
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 40.dp)
                    .clip(RoundedCornerShape(25.dp))
            ) {
                Scaffold { scaffold ->
                    scaffold.calculateBottomPadding()
                    Column(modifier = Modifier.fillMaxSize()) {
                        SearchTextField(
                            value = searchValue, onValueChange = { searchValue = it },
                            textColor = Color.Black, //MaterialTheme.colors.surface,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                                .clip(RoundedCornerShape(50))
                                .height(40.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search",
                                    tint = Color.Black,//MaterialTheme.colors.surface,
                                    modifier = Modifier.padding(horizontal = 3.dp)
                                )
                            },
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn {
                            items(
                                if (searchValue.isEmpty()) countryList else searchCountry(
                                    searchValue,
                                 //   context
                                )
                            ) { countryItem ->
                                Row(
                                    Modifier
                                        .padding(18.dp)
                                        .fillMaxWidth()
                                        .clickable(onClick = { onSelected(countryItem) }),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        modifier = modifier.width(30.dp),
                                        painter = painterResource(
                                            ConstData.LIB_COUNTRIES.single {
                                                it.countryCode == countryItem.countryCode
                                            }.flagDrawableResource

                                        ), contentDescription = null
                                    )
                                    Text(
                                        ConstData.LIB_COUNTRIES.single {
                                            it.countryCode == countryItem.countryCode
                                        }.countryName,
                                        Modifier.padding(horizontal = 18.dp),
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily.Serif,
                                    )
                                }
                            }
                        }
                    }

                }

            }
        },
    )
}


@Composable
private fun SearchTextField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    value: String,
    textColor: Color = Color.Black,
    onValueChange: (String) -> Unit,
    hint: String = StringRes.SEARCH,
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize
) {

    BasicTextField(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 18.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(  MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = textColor,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) Text(
                        hint,
                        style = LocalTextStyle.current.copy(
                            color = Color.Gray,
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}