package com.example.expandablebottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expandablebottomsheet.ui.theme.ExpandableBottomSheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpandableBottomSheetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExpandableBottomSheet(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
Objective:
Implement an expandable bottom sheet that the user can drag upward to reveal more content and
collapse downward. The sheet should smoothly animate and snap to either a collapsed or expanded
state once the drag ends.
*/
@Composable
fun ExpandableBottomSheet(modifier: Modifier = Modifier) {
    // Define the collapsed and expanded heights
    val collapsedHeight = 100.dp
    val expandedHeight = 800.dp

    // Current (mutable) height of the sheet
    var sheetHeight by remember { mutableStateOf(collapsedHeight) }
    // Animate transitions in sheet height
    val animatedSheetHeight by animateDpAsState(targetValue = sheetHeight)

    val density = LocalDensity.current

    Box(modifier = modifier.fillMaxSize()) {
        // 1) Place any "behind" content here (if desired):
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Behind Content", color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Scroll up the sheet to see more", color = Color.DarkGray)
        }

        // 2) Bottom Sheet on top, aligned to the bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(animatedSheetHeight)
                .background(Color.LightGray)
                // Use pointerInput to detect vertical drag gestures
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onVerticalDrag = { change, dragAmount ->
                            val dragDp = with(density) { dragAmount.toDp() }
                            sheetHeight = (sheetHeight - dragDp)
                                .coerceIn(collapsedHeight, expandedHeight)
                        },
                        onDragEnd = {
                            val midpoint = (collapsedHeight + expandedHeight) / 2
                            sheetHeight = if (sheetHeight > midpoint) expandedHeight else collapsedHeight
                        }
                    )
                }

        ) {
            // Bottom Sheet content
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Bottom Sheet Content", color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Draw up to show more", color = Color.DarkGray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpandableBottomSheetTheme {
        ExpandableBottomSheet()
    }
}
