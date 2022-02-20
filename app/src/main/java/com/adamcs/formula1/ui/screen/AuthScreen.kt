package com.adamcs.formula1.ui.screen

import android.app.Activity
import android.content.Context
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamcs.formula1.R
import com.adamcs.formula1.ui.theme.Purple700
import com.adamcs.formula1.ui.viewmodel.AuthViewModel

@ExperimentalComposeUiApi
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    activity: Activity
) {
    val context = LocalContext.current


    Column(
        modifier = Modifier
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

        Text(
            text = stringResource(R.string.formula1_title),
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.PLEASE_LOG_IN_OR_REGISTER),
            color = Color.Black,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        LoginPanel(viewModel, context = context, activity = activity)
    }
}

@ExperimentalComposeUiApi
@Composable
private fun LoginPanel(
    viewModel: AuthViewModel,
    context: Context,
    activity: Activity
) {
    val (focusEmail, focusPassword) = remember { FocusRequester.createRefs() }
    val focusColor = Purple700
    val baseColor = Color(0.247f, 0.318f, 0.71f, 1.0f)

    var isLogin by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (viewModel.errorMessage.value.isNotEmpty()) {
            Text(
                text = viewModel.errorMessage.value,
                color = Color.Red,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        EmailInputField(
            viewModel = viewModel,
            focusRequester = focusEmail,
            nextFocusRequester = focusPassword,
            focusColor = focusColor
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordInputField(
            viewModel = viewModel,
            focusRequester = focusPassword,
            focusColor = focusColor
        )

        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceEvenly,
        ) {
            TextButton(onClick = { isLogin = !isLogin }) {
                Text(
                    text = if (isLogin) stringResource(R.string.register) else stringResource(R.string.login),
                    fontSize = 12.sp,
                    color = baseColor
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                if (isLogin) {
                    viewModel.logInWithEmailAndPassword(
                        context = context,
                        activity = activity
                    )
                } else {
                    viewModel.createUserWithEmailAndPassword(
                        context = context,
                        activity = activity
                    )
                }

            },
            modifier = Modifier
                .width(180.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = baseColor
            )
        ) {
            Text(
                text = if (isLogin) stringResource(R.string.login) else stringResource(R.string.register),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OtherLoginTypes()
    }
}

@Composable
private fun EmailInputField(
    viewModel: AuthViewModel,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester,
    focusColor: Color
) {
    var isError by remember { mutableStateOf(false) }

    val currentColor = if (isError)
        Color.Red
    else
        focusColor

    OutlinedTextField(
        isError = isError,
        value = viewModel.emailString.value,
        onValueChange = {
            viewModel.emailString.value = it

            isError = !viewModel.isEmailValid()
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(onNext = { nextFocusRequester.requestFocus() }),
        singleLine = true,
        label = {
            Text(text = "E-mail")
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusColor,
            cursorColor = focusColor,
            focusedLabelColor = focusColor,
            textColor = currentColor,
        ),
        shape = RoundedCornerShape(30.dp)
    )
}

@ExperimentalComposeUiApi
@Composable
private fun PasswordInputField(
    viewModel: AuthViewModel,
    focusRequester: FocusRequester,
    focusColor: Color
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val keyboard = LocalSoftwareKeyboardController.current

    var isError by remember { mutableStateOf(false) }

    val currentColor = if (isError)
        Color.Red
    else
        focusColor

    OutlinedTextField(
        isError = isError,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = viewModel.passwordString.value,
        onValueChange = {
            viewModel.passwordString.value = it

            isError = !viewModel.isPasswordValid()
        },
        label = { Text(text = "Password") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { keyboard?.hide() }),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_eye),
                    contentDescription = "Password Toggle"
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusColor,
            cursorColor = focusColor,
            focusedLabelColor = focusColor,
            textColor = currentColor,
        ),
        shape = RoundedCornerShape(30.dp)
    )
}

@Composable
private fun OtherLoginTypes() {
    Row(
        modifier = Modifier.fillMaxWidth(0.7f),
        Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .height(45.dp)
                .width(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_fb),
                contentDescription = "Facebook logo",
                modifier = Modifier
                    .size(22.dp),
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(Color.Blue)
            )
        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .height(45.dp)
                .width(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Google logo",
                modifier = Modifier
                    .size(22.dp)
            )
        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .height(45.dp)
                .width(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = "Twitter logo",
                modifier = Modifier
                    .size(22.dp),
            )
        }

    }
}