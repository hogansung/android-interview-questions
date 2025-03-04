package com.example.customcircularprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.example.customcircularprogress.ui.theme.CustomCircularProgressTheme
import androidx.compose.foundation.Canvas
import androidx.compose.material3.Text
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    // Animate the progress value when it changes.
    // 使用 animateFloatAsState 將目標進度做動畫過渡
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000) // 動畫從開始到結束 的整個過程會持續 1 秒鐘
    )
    // Use Canvas to draw a background circle.
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(48.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // 設定進度指示器的畫筆寬度
            val strokeWidth = 64f  // 這裡使用像素值，也可以根據需要轉換 dp
            // 計算圓的半徑，預留出 stroke 的空間
            val radius = (size.minDimension - strokeWidth) / 2

            // 畫背景圓（灰色）
            drawCircle(
                color = Color.LightGray,
                radius = radius,
                center = center,
                style = Stroke(width = strokeWidth)
            )

            // Draw an arc representing the animated progress (from 0.0 to 1.0).
            // 根據 animatedProgress 畫進度弧線，從 -90°（即頂部）開始
            drawArc(
                color = Color.DarkGray,
                startAngle = -90f,
                sweepAngle = animatedProgress * 360,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                size = Size(radius * 2, radius * 2),
                topLeft = Offset(center.x - radius, center.y - radius)
            )
        }

        // Display the percentage (e.g., "75%") in the center.
        // 在圓心顯示百分比數字
        Text(
            text = "${(animatedProgress * 100).toInt()}%",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomCircularProgressTheme {
        CustomCircularProgress(progress = 0.75f)
    }
}