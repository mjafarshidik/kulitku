package com.developer.kulitku.ui.kubuku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.kubuku.KubukuResponse
import com.developer.kulitku.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KubukuViewModel() : ViewModel() {

    private val _listArticle = MutableLiveData<ResultState<List<KubukuResponse>>>()
    val getListArticle: LiveData<ResultState<List<KubukuResponse>>> = _listArticle

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val getErrorMessage: LiveData<String> = _errorMessage

    fun getAllArticle() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val article = ApiConfig.getApiService().getAllArticle()
                val data = article.data
                if (data != null) {
                    _listArticle.postValue(ResultState.Success(data))
                }
            } catch (e: Exception) {
                _listArticle.postValue(ResultState.Failure(e))
            }
        }
    }
}