package com.adamcs.formula1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adamcs.formula1.R
import com.adamcs.formula1.ui.theme.Purple700

@ExperimentalComposeUiApi
@Composable
fun AuthScreen() {
    val context = LocalContext.current


    Column(modifier = Modifier
        .fillMaxHeight(1f)
        .fillMaxWidth(1f)
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(1f, 1f, 1f, 1f),
                    Color(0.8f, 0.8f, 0.8f, 1f),
                ),
                startY = 500f
            )
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.forma1car), contentDescription = "Top Car",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(1f)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Formula 1", color = Color.Black, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Please log in or register", color = Color.Black, fontSize = 15.sp)

        Spacer(modifier = Modifier.height(30.dp))

        LoginPanel()
    }
}

@ExperimentalComposeUiApi
@Composable
private fun LoginPanel(){
    var emailString by remember { mutableStateOf("") }
    var passwordString by remember { mutableStateOf("") }
    var isPasswordVisible by remember{ mutableStateOf(false) }

    val (focusEmail, focusPassword) = remember { FocusRequester.createRefs() }

    val keyboard = LocalSoftwareKeyboardController.current
    
    val focusColor = Purple700

    Column(modifier = Modifier
        .fillMaxWidth(0.8f)
        .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = emailString,
            onValueChange = { emailString = it },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusEmail),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onNext = {focusPassword.requestFocus()}),
            singleLine = true,
            label = {
                Text(text = "E-mail")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = focusColor,
                cursorColor = focusColor,
                focusedLabelColor = focusColor
            ),
            shape = RoundedCornerShape(30.dp)
        )
        
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusPassword),
            value = passwordString,
            onValueChange ={passwordString = it},
            label = { Text(text = "Password")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions ( onDone = { keyboard?.hide() } ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible}) {
                    Icon(painter = painterResource(R.drawable.ic_baseline_eye),
                        contentDescription ="Password Toggle" )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = focusColor,
                cursorColor = focusColor,
                focusedLabelColor = focusColor
            ),
            shape = RoundedCornerShape(30.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.Center,
        ){
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Forgot Password", fontSize = 12.sp, color = Color(0.247f, 0.318f, 0.71f, 1.0f))
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .width(180.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0.247f, 0.318f, 0.71f, 1.0f)
            )
        ) {
            Text(text = "Log in", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        OtherLoginTypes()
    }
}

@Composable
private fun OtherLoginTypes(){
    Row(modifier = Modifier.fillMaxWidth(0.7f),
        Arrangement.SpaceEvenly){
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .height(45.dp)
                .width(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_fb), contentDescription = "Facebook logo",
                modifier = Modifier
                    .size(22.dp),
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(Color.Blue)
            )
        }
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .height(45.dp)
                .width(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "Google logo",
                modifier = Modifier
                    .size(22.dp)
            )
        }
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .height(45.dp)
                .width(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_twitter), contentDescription = "Twitter logo",
                modifier = Modifier
                    .size(22.dp),
            )
        }

    }
}