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
import com.adamcs.formula1.MainActivity
import com.adamcs.formula1.data.model.News
import com.google.android.gms.tasks.Task


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {
    private val TAG = "AUTH_VIEW_MODEL"

    var user = mutableStateOf<FirebaseUser?>(null)


    fun isUserLoggedIn(): Boolean {
        return user.value != null
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        activity: Activity,
        context: Context
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                activity
            ) { task ->
                handleResult(context = context, result = task)
            }
    }

    private fun handleResult(context: Context, result: Task<AuthResult>) {
        if (result.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            Log.d(TAG, "createUserWithEmail:success")
            user.value = auth.currentUser

            context.startActivity(Intent(context, MainActivity::class.java))
        } else {
            // If sign in fails, display a message to the user.
            Log.w(TAG, "createUserWithEmail:failure", result.exception)
        }

    }
}