package com.example.piechart

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
import com.example.piechart.ui.theme.PieChartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PieChartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PieChart(data = listOf("Label 1" to 0.2f, "Label 2" to 0.3f, "Label 3" to 0.5f))
                }
            }
        }
    }
}

/*
Objective:
Using Compose’s Canvas, draw a pie chart based on a list of label–value pairs. Animate the drawing
of each slice and detect tap gestures on a slice to display its details (for example, as a tooltip
in the center).
*/

@Composable
fun PieChart(data: List<Pair<String, Float>>) {
    // TODO: Draw a pie chart using Canvas based on the provided data.
    // TODO: Animate the drawing of each slice.
    // TODO: Detect tap gestures on slices and display a tooltip with the slice's label and value.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PieChartTheme {
        PieChart(data = listOf("Label 1" to 0.2f, "Label 2" to 0.3f, "Label 3" to 0.5f))
    }
}