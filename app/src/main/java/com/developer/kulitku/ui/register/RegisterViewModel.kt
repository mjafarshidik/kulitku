package com.developer.kulitku.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.SignUpBody
import com.developer.kulitku.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel() : ViewModel() {
    private val _nameError = MutableLiveData<String>()
    val nameError: LiveData<String> = _nameError

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    private val _genderError = MutableLiveData<String>()
    val genderError: LiveData<String> = _genderError

    private val _birthdateError = MutableLiveData<String>()
    val birthdateError: LiveData<String> = _birthdateError

    private val _complainError = MutableLiveData<String>()
    val complainError: LiveData<String> = _complainError

    private val _signUpStatus = MutableLiveData<ResultState<Boolean>>()
    val signUpStatus: LiveData<ResultState<Boolean>> = _signUpStatus

    fun signUp(
        nama: String,
        email: String,
        jenisKelamin: String,
        jenisKulit: String,
        keluhan: String,
        tanggalLahir: String,
        pass: String,
    ) {
        if (nama == "" || nama.isEmpty()) {
            _nameError.value = "Silakan masukkan nama terlebih dahulu!"
        } else if (jenisKelamin == "" || jenisKelamin.isEmpty()) {
            _genderError.value = "Silakan masukkan jenis kelamin terlebih dahulu!"
        } else if (tanggalLahir == "" || tanggalLahir.isEmpty()) {
            _birthdateError.value = "Silakan masukkan tanggal lahir terlebih dahulu!"
        } else if (pass == "" || pass.isEmpty()) {
            _passwordError.value = "Silakan masukkan password terlebih dahulu!"
        } else if (email == "" || email.isEmpty()) {
            _emailError.value = "Silakan masukkan email terlebih dahulu"
        } else {
            pushSignUp(
                nama, email, jenisKelamin, jenisKulit, keluhan, tanggalLahir, pass
            )
        }
    }

    fun pushSignUp(
        nama: String,
        email: String,
        jenisKelamin: String,
        jenisKulit: String,
        keluhan: String,
        tanggalLahir: String,
        pass: String
    ) {
        _signUpStatus.value = ResultState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val body = SignUpBody(nama, email, jenisKelamin, jenisKulit, keluhan, tanggalLahir, pass)
            try {
                val signUp = ApiConfig.getApiService().signUp(body)
                val data = signUp.message
                if (data != null) {
                    _signUpStatus.postValue(ResultState.Success(true))
                }
            } catch (e: Exception) {
                _signUpStatus.postValue(ResultState.Failure(e))
            }
        }
    }
}