package com.developer.kulitku.ui.scan.result

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.kulitku.data.source.local.SharedPrefs
import com.developer.kulitku.data.source.remote.RecommendationIngredientResponse
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.ScanResponse
import com.developer.kulitku.data.source.remote.SuggestionResponse
import com.developer.kulitku.data.source.remote.signin.SignInResponse
import com.developer.kulitku.network.ApiConfig
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream


class ScanViewModel : ViewModel() {
    private val _labelState = MutableLiveData<ResultState<ScanResponse>>()
    val labelState: LiveData<ResultState<ScanResponse>> = _labelState

    private val _recommendationState =
        MutableLiveData<ResultState<List<RecommendationIngredientResponse>>>()
    val recommendationState: LiveData<ResultState<List<RecommendationIngredientResponse>>> =
        _recommendationState

    private val _suggestionState = MutableLiveData<ResultState<List<SuggestionResponse>>>()
    val suggestionState: LiveData<ResultState<List<SuggestionResponse>>> =
        _suggestionState

    fun uploadImage(file: File) {
        _labelState.value = ResultState.Loading
        val result = BitmapFactory.decodeFile(file.path)
        val compressQuality = 100
        result.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))

        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "uploads",
            file.name,
            requestImageFile
        )

        val userData: SignInResponse = Hawk.get(SharedPrefs.KEY_LOGIN)

        val userId: MultipartBody.Part = MultipartBody.Part.createFormData(
            "user_id",
            userData.userId.toString(),
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val register = ApiConfig.getApiService().uploadImage(userId, imageMultipart)
                val data = register.data
                if (data != null) {
                    _labelState.postValue(ResultState.Success(data))
                    _recommendationState.postValue(ResultState.Success(data.kandungan))
                    _suggestionState.postValue(ResultState.Success(data.saran))
                }
            } catch (e: Exception) {
                _labelState.postValue(ResultState.Failure(e))
            }
        }
    }
}