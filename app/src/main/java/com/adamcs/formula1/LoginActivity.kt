package com.adamcs.formula1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.ExperimentalComposeUiApi
import com.adamcs.formula1.ui.screen.AuthScreen
import com.adamcs.formula1.ui.theme.Formula1Theme
import com.adamcs.formula1.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: ComponentActivity() {

    val viewModel by viewModels<AuthViewModel>()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Formula1Theme {
                AuthScreen()
            }
        }
    }
}