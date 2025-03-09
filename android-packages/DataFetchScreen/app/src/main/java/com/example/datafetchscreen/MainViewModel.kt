package com.example.datafetchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepository = DataRepository()): ViewModel() {
    private val _uiState = MutableStateFlow<DataFetchUiState>(DataFetchUiState.Loading)
    val uiState: StateFlow<DataFetchUiState> = _uiState

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _uiState.value = DataFetchUiState.Loading
            try {
                val data = repository.fetchData()
                _uiState.value = DataFetchUiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = DataFetchUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // 重試函式，方便 UI 呼叫以重新抓取資料
    fun retry() {
        fetchData()
    }
}

sealed class DataFetchUiState {
    object Loading: DataFetchUiState()
    data class Success(val data: List<String>): DataFetchUiState()
    data class Error(val message: String): DataFetchUiState()
}