package com.example.piechart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.piechart.ui.theme.PieChartTheme
import kotlinx.coroutines.delay
import kotlin.math.atan2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PieChartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PieChart(Modifier.padding(innerPadding), data = listOf("Label 1" to 0.2f, "Label 2" to 0.3f, "Label 3" to 0.5f))
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
fun PieChart(modifier: Modifier = Modifier, data: List<Pair<String, Float>>) {
    // 計算總值，避免除以 0
    val total = data.sumOf { it.second.toDouble() }.toFloat().coerceAtLeast(1f)

    // Animate the drawing of each slice.
    // 每個 slice 對應的目標角度：比例乘上 360 度
    val targetAngles = data.map { (it.second / total) * 360f }
    // 為每個 slice 建立一個 Animatable 狀態，初始值為 0 度
    val animatedAngles = remember(data) {
        targetAngles.map { androidx.compose.animation.core.Animatable(0f) }
    }

    // 依序動畫顯示每個 slice 的角度
    LaunchedEffect(data) {
        animatedAngles.forEachIndexed { index, animatable ->
            animatable.animateTo(
                targetValue = targetAngles[index],
                animationSpec = tween(durationMillis = 500)
            )
            // 每個 slice 間可以加個延遲，讓動畫更有順序感
            delay(100)
        }
    }

    // State for the selected slice (if any) for tooltip display
    var selectedSliceIndex by remember { mutableStateOf<Int?>(null) }

    // Use BoxWithConstraints to overlay the tooltip and detect tap gestures.
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        // Convert constraints to pixels for gesture calculations.
        val density = LocalDensity.current
        val canvasWidthPx = with(density) { maxWidth.toPx() }
        val canvasHeightPx = with(density) { maxHeight.toPx() }
        val centerX = canvasWidthPx / 2
        val centerY = canvasHeightPx / 2

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(data) {
                    detectTapGestures { offset ->
                        // Calculate the tap's angle relative to the canvas center
                        val x = offset.x - centerX
                        val y = offset.y - centerY
                        var tapAngle = Math.toDegrees(atan2(y, x).toDouble()).toFloat()
                        if (tapAngle < 0f) tapAngle += 360f

                        // Pie chart starts at 270° (12 o'clock)
                        var currentAngle = 270f
                        data.forEachIndexed { index, (_, value) ->
                            val sliceAngle = (value / total) * 360f
                            if (tapAngle >= currentAngle && tapAngle < currentAngle + sliceAngle) {
                                selectedSliceIndex = index
                                return@detectTapGestures
                            }
                            currentAngle += sliceAngle
                        }
                    }
                }
        ) {
            val radius = size.minDimension / 2
            val topLeft = Offset(center.x - radius, center.y - radius)
            var startAngle = 270f  // 從 270 度開始 (即 12 點方向)

            animatedAngles.forEachIndexed { index, animatable ->
                drawArc(
                    color = when (index) {
                        0 -> Color.Gray
                        1 -> Color.LightGray
                        2 -> Color.DarkGray
                        else -> Color.Black
                    },
                    startAngle = startAngle,
                    sweepAngle = animatable.value,
                    useCenter = true,
                    topLeft = topLeft,
                    size = Size(radius * 2, radius * 2)
                )
                // 更新下一片的起始角度
                startAngle += animatable.value
            }
        }

        // Display tooltip in the center if a slice is selected
        selectedSliceIndex?.let { index ->
            val (label, value) = data[index]
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(text = "$label: $value", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PieChartTheme {
        PieChart(data = listOf("Label 1" to 0.2f, "Label 2" to 0.3f, "Label 3" to 0.5f))
    }
}