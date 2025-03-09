package com.example.datafetchscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<DataFetchUiState>(DataFetchUiState.Loading)
    val uiState: StateFlow<DataFetchUiState> = _uiState


}

sealed class DataFetchUiState {
    object Loading: DataFetchUiState()
    data class Success(val data: List<String>): DataFetchUiState()
    data class Error(val data: List<String>): DataFetchUiState()
}