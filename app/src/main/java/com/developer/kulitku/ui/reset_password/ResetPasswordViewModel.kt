package com.developer.kulitku.ui.reset_password

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResetPasswordViewModel : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    private val _resetStatus = MutableLiveData<Boolean>()
    val resetStatus: LiveData<Boolean> = _resetStatus

    fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _resetStatus.value = true
                } else {
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    _resetStatus.value = false
                }
            }
    }
}