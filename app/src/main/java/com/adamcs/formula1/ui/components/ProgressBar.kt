package com.adamcs.formula1.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressBar(
    height: Dp, width: Dp, backgroundColor: Color, foregroundColor: Brush, percent: Int, shape: Shape,
    middleText: String = ""
) {
    Box(
        modifier = Modifier
            .shadow(6.dp, shape = shape)
            .background(backgroundColor, shape = shape)
            .width(width)
            .height(height = height)
            //.clip(shape = RoundedCornerShape(size = 5.dp))
    ) {
        Box(
            modifier = Modifier
                .background(foregroundColor, shape = shape)
                .width(width * percent / 100)
                .height(height = height)
                //.clip(shape = RoundedCornerShape(size = 5.dp))
        )

        Text(
            middleText,
            modifier = Modifier.
            align(alignment = Alignment.Center),
            fontSize = 12.sp
        )
    }
}