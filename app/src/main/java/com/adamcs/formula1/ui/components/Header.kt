package com.adamcs.formula1.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adamcs.formula1.R

@Composable
fun Header (
    title: String
){
    Box(modifier = Modifier.fillMaxHeight(0.3f)){
        CurvedHeaderBackground()
        Image(
            modifier = Modifier.align(Alignment.BottomStart)
                .width(330.dp)
                .padding(vertical = 20.dp),
            painter = painterResource(id = R.drawable.f1_car),
            contentDescription = "F1 car",
        )
        Text(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            text = title,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 20.dp)
        )
    }



}


@Composable
fun CurvedHeaderBackground() {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        onDraw = {
            drawPath(
                path = Path().apply {
                    moveTo(0.0f, 0.0f)
                    lineTo(0.0f, size.height - 40);
                    quadraticBezierTo(
                        size.width / 2, size.height, size.width, size.height - 100);
                    lineTo(size.width, 0.0f);
                    close()
                },
                Color.Black,
            )
        }
    )
}