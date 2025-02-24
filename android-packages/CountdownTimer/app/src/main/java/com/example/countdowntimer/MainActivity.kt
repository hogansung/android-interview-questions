package com.example.countdowntimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.countdowntimer.ui.theme.CountdownTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountdownTimerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CountdownTimer()
                }
            }
        }
    }
}

@Composable
fun CountdownTimer() {
    // TODO: Create a state variable for the countdown starting at 10.
    // TODO: Use a side-effect (e.g., LaunchedEffect) to update the timer every second.
    // TODO: When the countdown reaches 0, display "Time's up!" and a restart button.

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TODO: Display the countdown or "Time's up!" based on timer value.
        // TODO: Display a restart button when the timer is 0.
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CountdownTimerTheme {
        CountdownTimer()
    }
}