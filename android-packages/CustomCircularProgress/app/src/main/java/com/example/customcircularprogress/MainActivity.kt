package com.example.customcircularprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.customcircularprogress.ui.theme.CustomCircularProgressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomCircularProgressTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CustomCircularProgress(progress = 0.75f, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
Objective:
Create a custom circular progress indicator that animates its progress from 0 to a target value and
displays the percentage inside the circle. Use Compose’s Canvas to draw the indicator and animate
the progress.
*/
@Composable
fun CustomCircularProgress(
    progress: Float,
    modifier: Modifier = Modifier
    ) {
    // TODO: Animate the progress value when it changes.
    // TODO: Use Canvas to draw a background circle.
    // TODO: Draw an arc representing the animated progress (from 0.0 to 1.0).
    // TODO: Display the percentage (e.g., "75%") in the center.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomCircularProgressTheme {
        CustomCircularProgress(progress = 0.75f)
    }
}