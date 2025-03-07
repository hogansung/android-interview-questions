package com.example.datafetchscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    // TODO: In the composable, collect the data state.
    // TODO: Show a CircularProgressIndicator while loading.
    // TODO: Display the list of data or an error message with a retry button.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataFetchScreenTheme {
        DataFetchScreen()
    }
}