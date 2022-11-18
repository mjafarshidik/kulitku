package com.developer.kulitku.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class ScanViewModel() : ViewModel() {
    private val _uploadStatus = MutableLiveData<ResultState<Boolean>>()
    val uploadStatus: LiveData<ResultState<Boolean>> = _uploadStatus

    fun uploadImage(file: File) {
        _uploadStatus.value = ResultState.Loading

        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "uploads",
            file.name,
            requestImageFile
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val register = ApiConfig.getApiService().uploadImage(imageMultipart)
                val data = register.data
                if (data != null) {
                    _uploadStatus.postValue(ResultState.Success(true))
                }
                Log.d("MILO", data?.message.toString())
            } catch (e: Exception) {
                _uploadStatus.postValue(ResultState.Failure(e))
            }
        }
    }
}