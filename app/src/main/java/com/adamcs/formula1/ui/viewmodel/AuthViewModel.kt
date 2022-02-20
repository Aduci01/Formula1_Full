package com.adamcs.formula1.ui.viewmodel

import android.R.attr
import androidx.lifecycle.ViewModel
import com.adamcs.formula1.data.repository.NewsRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.widget.Toast

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult

import com.google.android.gms.tasks.OnCompleteListener

import android.R.attr.password
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adamcs.formula1.MainActivity
import com.adamcs.formula1.data.model.News
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {
    private val TAG = "AUTH_VIEW_MODEL"

    var user = mutableStateOf<FirebaseUser?>(null)

    var emailString = mutableStateOf("")
    var passwordString = mutableStateOf("")

    var errorMessage = mutableStateOf("")

    fun isUserLoggedIn(): Boolean {
        return user.value != null
    }

    fun createUserWithEmailAndPassword(
        activity: Activity,
        context: Context
    ) {
        if (!isEmailValid() || !isPasswordValid()) return

        auth.createUserWithEmailAndPassword(emailString.value, passwordString.value)
            .addOnCompleteListener(
                activity
            ) { task ->
                handleResult(context = context, result = task)
            }
    }

    fun logInWithEmailAndPassword(
        activity: Activity,
        context: Context
    ) {
        if (!isEmailValid() || !isPasswordValid()) return

        auth.signInWithEmailAndPassword(emailString.value, passwordString.value)
            .addOnCompleteListener(
                activity
            ) { task ->
                handleResult(context = context, result = task)
            }
    }

/*
    fun logInWithGoogle(
        activity: Activity,
        context: Context
    ) {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("392916505764-pl2k16l620b09aeopgrnvm2qhnmg7a1c.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }

    private fun firebaseAuthWithGoogle(idToken: String, activity: Activity) {
        var googleSignInClient = GoogleSignIn.getClient(this, gso)

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    user.value = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }*/

    private fun handleResult(context: Context, result: Task<AuthResult>) {
        if (result.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            Log.d(TAG, "createUserWithEmail:success")
            user.value = auth.currentUser

            context.startActivity(Intent(context, MainActivity::class.java))
        } else {
            // If sign in fails, display a message to the user.
            Log.w(TAG, "createUserWithEmail:failure", result.exception)
            errorMessage.value = result.exception?.message.toString()
        }
    }

    fun isEmailValid() : Boolean {
        val email = emailString.value

        if (email.length < 3) {
            return false
        }

        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return EMAIL_REGEX.toRegex().matches(email)
    }

    fun isPasswordValid() : Boolean {
        val pw = passwordString.value

        if (pw.length < 6) {
            return false
        }

        return true
    }
}