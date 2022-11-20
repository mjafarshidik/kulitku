package com.developer.kulitku.ui.register

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.SignUpBody
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterViewModel() : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    private val _registerStatus = MutableLiveData<Boolean>()
    val registerStatus: LiveData<Boolean> = _registerStatus

    fun register(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    _registerStatus.value = true
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    _registerStatus.value = false
                }
            }
    }

//    private lateinit var database: DatabaseReference
//
//    private val _nameError = MutableLiveData<String>()
//    val nameError: LiveData<String> = _nameError
//
//    private val _emailError = MutableLiveData<String>()
//    val emailError: LiveData<String> = _emailError
//
//    private val _passwordError = MutableLiveData<String>()
//    val passwordError: LiveData<String> = _passwordError
//
//    private val _genderError = MutableLiveData<String>()
//    val genderError: LiveData<String> = _genderError
//
//    private val _birthdateError = MutableLiveData<String>()
//    val birthdateError: LiveData<String> = _birthdateError
//
//    private val _registerStatus = MutableLiveData<ResultState<Boolean>>()
//    val registerStatus: LiveData<ResultState<Boolean>> = _registerStatus
//
//    fun register(
//        name: String,
//        gender: String,
//        birthdate: String,
//        email: String,
//        password: String
//    ) {
//        if (name == "" || name.isEmpty()) {
//            _nameError.value = "Silakan masukkan nama terlebih dahulu!"
//        } else if (gender == "" || name.isEmpty()) {
//            _genderError.value = "Silakan masukkan jenis kelamin terlebih dahulu!"
//        } else if (birthdate == "" || birthdate.isEmpty()) {
//            _birthdateError.value = "Silakan masukkan tanggal lahir terlebih dahulu!"
//        } else if (password == "" || password.isEmpty()) {
//            _passwordError.value = "Silakan masukkan password terlebih dahulu!"
//        } else if (email == "" || email.isEmpty()) {
//            _emailError.value = "Silakan masukkan email terlebih dahulu"
//        } else {
//            pushRegister(
//                name, email, password, birthdate, gender
//            )
//        }
//    }
//
//    fun pushRegister(
//        name: String,
//        email: String,
//        password: String,
//        birthdate: String,
//        gender: String
//        ) {
//        database = FirebaseDatabase.getInstance("https://kulitku.firebaseio.com").reference
//
//        val id: String? = database.push().key
//        val user = SignUpBody(id!!, name, email, password, birthdate, gender)
//
//        try {
//            database.child(id).setValue(user)
//                .addOnSuccessListener {
//                    // Write was successful!
//                    _registerStatus.postValue(ResultState.Success(true))
//                }
//        } catch (e: Exception) {
//            _registerStatus.postValue(ResultState.Failure(e))
//        }
//    }
}