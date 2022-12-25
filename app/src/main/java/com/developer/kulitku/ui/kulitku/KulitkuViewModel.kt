package com.developer.kulitku.ui.kulitku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.kulitku.KulitkuResponse
import com.developer.kulitku.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KulitkuViewModel(): ViewModel() {
    private val _dataHistory = MutableLiveData<ResultState<List<KulitkuResponse>>>()
    val dataHistory : LiveData<ResultState<List<KulitkuResponse>>> = _dataHistory
    
    fun getHistoryScan(userId: Int) {
        _dataHistory.value = ResultState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val history = ApiConfig.getApiService().getHistoryScan(userId)
                val data = history.data
                if (data != null) {
                    _dataHistory.postValue(ResultState.Success(data))
                }
            } catch (e: Exception) {
                _dataHistory.postValue(ResultState.Failure(e))
            }
        }
    }
}