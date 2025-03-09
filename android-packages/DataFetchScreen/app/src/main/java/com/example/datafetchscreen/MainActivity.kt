package com.example.datafetchscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datafetchscreen.ui.theme.DataFetchScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataFetchScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DataFetchScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
Objective:
Create a screen that fetches data from a simulated repository using a ViewModel and Kotlin Flow.
The screen should display a loading indicator during data retrieval, show data on success, and
display an error message with a retry button if the fetch fails.
*/

@Composable
fun DataFetchScreen(modifier: Modifier = Modifier) {
    // TODO: Create a ViewModel that simulates data fetching (using delay) and exposes a Flow or LiveData.
    val viewModel: MainViewModel = viewModel()

    // TODO: In the composable, collect the data state.
    val uiState = viewModel.uiState.collectAsState() // 取得 UI 狀態（Flow -> State）

    // TODO: Show a CircularProgressIndicator while loading.


    // TODO: Display the list of data or an error message with a retry button.
    when (val state = uiState.value) {
        is DataFetchUiState.Loading -> {
            // 載入中，顯示一個 CircularProgressIndicator
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DataFetchUiState.Success -> {
            // 成功取得資料，假設資料為 List<String>，使用 LazyColumn 顯示
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(state.data) { item ->
                    Text(
                        text = item,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        is DataFetchUiState.Error -> {
            // 發生錯誤，顯示錯誤訊息與重試按鈕
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Error: ${state.message}")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(onClick = { viewModel.retry() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataFetchScreenTheme {
        DataFetchScreen()
    }
}