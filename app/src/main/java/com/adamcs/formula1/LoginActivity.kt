package com.adamcs.formula1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.app.ActivityCompat
import com.adamcs.formula1.ui.screen.AuthScreen
import com.adamcs.formula1.ui.theme.Formula1Theme
import com.adamcs.formula1.ui.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: ComponentActivity() {
    val viewModel: AuthViewModel by viewModels<AuthViewModel>()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Formula1Theme {
                AuthScreen(activity = this, viewModel = viewModel){
                    googleSignIn(this)
                }
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("LOGIN", "firebaseAuthWithGoogle:" + account.id)
                viewModel.firebaseAuthWithGoogle(account.idToken!!, activity = this)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("LOGIN", "Google sign in failed", e)
            }
        }
    }

    fun googleSignIn(activity: Activity){
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("392916505764-pl2k16l620b09aeopgrnvm2qhnmg7a1c.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(activity, gso)

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}