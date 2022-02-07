package com.adamcs.formula1.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.adamcs.formula1.ui.theme.FormulaFont

@Composable
fun SearchBar(
    modifier: Modifier,
    placeholderText: String,
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    var isPlaceholderDisplayed by remember {
        mutableStateOf(placeholderText != "")
    }


    Box(modifier = modifier){
        BasicTextField(value = text, onValueChange = {
            text = it
            onSearch(it)

            isPlaceholderDisplayed = text.isEmpty()
        },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.Black, fontFamily = FormulaFont),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 40.dp, 12.dp)
        )


        if (isPlaceholderDisplayed){
            Text(
                text = placeholderText,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 12.dp)
            )
        }

        Icon(
            Icons.Default.Search, contentDescription = "Magnifying glass",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 8.dp))
    }
}