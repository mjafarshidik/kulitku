package com.developer.kulitku.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.kulitku.data.source.local.SharedPrefs.Companion.KEY_LOGIN
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.signin.SignInBody
import com.developer.kulitku.network.ApiConfig
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    private val _emailError = MutableLiveData<String>()
    val emailError : LiveData<String> = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError : LiveData<String> = _passwordError

    private val _signInStatus = MutableLiveData<ResultState<Boolean>>()
    val signInStatus: LiveData<ResultState<Boolean>> = _signInStatus

    fun signIn(email: String, pass: String) {
        if (email == "" || email.isEmpty()) {
            _emailError.value = "Silakan masukkan Email terlebih dahulu!"
        } else if (pass == "" || pass.isEmpty()) {
            _passwordError.value = "Silakan masukkan password terlebih dahulu!"
        } else {
            pushSignIn(email, pass)
        }
    }

    fun pushSignIn(email: String, pass: String) {
        _signInStatus.value = ResultState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val body = SignInBody(email, pass)
            try {
                val login = ApiConfig.getApiService().signIn(body)
                val data = login.data
                if (data != null) {
                    Hawk.put(KEY_LOGIN, data)
                    _signInStatus.postValue(ResultState.Success(true))
                } else {
                    _signInStatus.postValue((ResultState.Failure(Exception(login.message ?: "Unknown Error"))))
                }
            } catch (e: Exception) {
                _signInStatus.postValue(ResultState.Failure(e))
            }
        }
    }
}