package com.adamcs.formula1.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adamcs.formula1.R
import com.adamcs.formula1.data.model.ConstructorResult
import com.adamcs.formula1.data.model.DriverResult
import com.adamcs.formula1.ui.theme.Shapes
import com.adamcs.formula1.ui.theme.scarlet

@Composable
fun ConstructorRankingCard(
    constructorResult: ConstructorResult,
    shape: Shape = Shapes.small,
    height: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .shadow(elevation = 4.dp, shape = Shapes.small)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        constructorResult.constructor.resources.color,
                        constructorResult.constructor.resources.color.copy(alpha = 0.5f)
                    ),
                    center = Offset(100f, 100f),
                    radius = 1000.0f
                )
            )
            .height(height.dp)
            .clip(shape = shape),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .background(Color.Transparent)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            FirstRow(constructorResult = constructorResult)
            SecondRow(constructorResult = constructorResult)
        }

        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)){

            Image(
                painter = painterResource(id = constructorResult.constructor.resources.logoId),
                contentDescription = "Constructor Image",
                Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight(0.6f)
                    .padding(horizontal = 20.dp),
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

@Composable
private fun FirstRow(constructorResult: ConstructorResult){
    val resources = constructorResult.constructor.resources

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        
        Text(
            text = "#" + constructorResult.position.toString(),
            color = Color.White,
            fontSize = 20.sp
        )

        Divider(
            modifier = Modifier
                .height(30.dp)
                .width(2.dp)
                .offset(x = 10.dp),
            color = Color(Math.min(1f, resources.color.red * 1.2f), Math.min(1f, resources.color.green * 1.2f), Math.min(1f, resources.color.blue * 1.2f)),
            thickness = 3.dp
        )

        Column(
            modifier = Modifier
                .offset(x = 22.dp)
                .height(40.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = constructorResult.constructor.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
private fun SecondRow(constructorResult: ConstructorResult){
    Row(
        modifier = Modifier
            .fillMaxHeight(1.0f)
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = constructorResult.points.toString() + " pts",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            overflow = TextOverflow.Visible,
        )
    }
}