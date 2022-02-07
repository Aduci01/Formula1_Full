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
import com.adamcs.formula1.data.model.DriverResult
import com.adamcs.formula1.ui.theme.Shapes
import com.adamcs.formula1.ui.theme.scarlet

@Composable
fun RankingCard(
    driverResult: DriverResult,
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
                        driverResult.constructors[0].resources.color,
                        driverResult.constructors[0].resources.color.copy(alpha = 0.3f)
                    ),
                    center = Offset(100f, 100f),
                    radius = 1200.0f
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

            FirstRow(driverResult = driverResult)
            SecondRow(driverResult = driverResult)
        }

        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)){
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(x = 50.dp, y = 15.dp),
                text = driverResult.driver.permanentNumber.toString(),
                fontSize = 90.sp,
                color = driverResult.constructors[0].resources.color.copy(alpha = 0.3f),
            )

            Image(
                painter = painterResource(id = driverResult.driver.imageId),
                contentDescription = "Driver Image",
                Modifier
                    .align(Alignment.BottomEnd)
                    .height(170.dp) ,
                contentScale = ContentScale.FillHeight

            )
        }
    }
}

@Composable
private fun FirstRow(driverResult: DriverResult){
    val resources = driverResult.constructors[0].resources

    Row(

    ) {
        Image(
            painter = painterResource(id = resources.logoId),
            contentDescription = "Team Logo",
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .offset(x = 5.dp)
                .width(50.dp)
                .height(40.dp),
            //alignment = Alignment.
        )

        Divider(
            modifier = Modifier
                .height(40.dp)
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
                text = driverResult.driver.givenName,
                color = Color(1.0f, 1.0f, 1.0f, 1f),
                fontSize = 12.sp
            )

            Text(
                text = driverResult.driver.familyName,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
private fun SecondRow(driverResult: DriverResult){
    Row(
        modifier = Modifier
            .fillMaxHeight(1.0f)
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.33f)) {
            Text(
                text = "#" + driverResult.position,
                color = Color(1.0f, 1.0f, 1.0f, 0.8f),
                fontSize = 17.sp
            )

            Text(
                text = driverResult.points.toString() + " pts",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                overflow = TextOverflow.Visible,
            )
        }
    }
}