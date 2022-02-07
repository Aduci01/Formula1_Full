package com.adamcs.formula1.ui.components

import android.view.KeyEvent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.adamcs.formula1.R
import com.adamcs.formula1.ui.theme.Shapes
import com.adamcs.formula1.ui.theme.gold
import java.lang.NumberFormatException

@Composable
fun DropDownMenu(
    errorMessage: String,
    baseColor: Color = gold,
    shape: Shape = Shapes.small,

    onSelectItem: (Int) -> Unit = {}
) {

    var expanded by remember { mutableStateOf(false) }
    val seasons = Array(63) { i -> 1960 + i }
    var selectedSeason by remember { mutableStateOf("2021") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    var isError by remember { mutableStateOf(false) }

    val currentColor = if (isError)
        Color.Red
    else
        baseColor

    Column(
        Modifier
            .padding(20.dp)
            .width(200.dp)
            .height(60.dp)
            .onGloballyPositioned { coordinates ->
                textfieldSize = coordinates.size.toSize()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = selectedSeason,
            onValueChange = {
                if (it.length <= 4){
                    selectedSeason = it
                    isError = !isInputValid(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                }
                .onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                    if (isInputValid(selectedSeason))
                        onSelectItem(selectedSeason.toInt())
                    true
                }
                false
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = baseColor,
                unfocusedBorderColor = baseColor,
                unfocusedLabelColor = baseColor,
                focusedLabelColor = baseColor,
                textColor = currentColor,
                cursorColor = currentColor,
            ),
            label = {
                Text(stringResource(R.string.season))
            },
            trailingIcon = {
                Icon(
                    icon, "Dropdown Arrow",
                    Modifier.clickable { expanded = !expanded },
                    tint = currentColor
                )
            },
            shape = shape,
            maxLines = 1,
        )

        if (isError) {
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                .height(300.dp)
        ) {
            seasons.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedSeason = label.toString()
                    expanded = false

                    onSelectItem(label)
                }) {
                    Text(text = label.toString())
                }
            }
        }
    }
}

private fun isInputValid(input: String): Boolean {
    try {
        val n = input.toInt()

        if (n < 1960 || n > 2022) return false
    } catch (e: NumberFormatException) {
        return false
    }

    return true
}