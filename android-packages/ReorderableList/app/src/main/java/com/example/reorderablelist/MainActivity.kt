package com.example.reorderablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.reorderablelist.ui.theme.ReorderableListTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReorderableListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ReorderableList(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
Objective:
Build a list of items that the user can reorder by dragging and dropping.
The following implementation creates a stateful list of items, displays them in a LazyColumn,
and uses drag gestures along with animated item placement to allow interactive reordering.
*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReorderableList(modifier: Modifier = Modifier) {
    // 1. Create a stateful list of items
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5") }
    // Variables to track dragging state
    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }
    var dragOffsetY by remember { mutableStateOf(0f) }

    // Assume each item has a fixed height
    val itemHeight = 56.dp
    val itemHeightPx = with(LocalDensity.current) { itemHeight.toPx() }

    LazyColumn(modifier = modifier) {
        itemsIndexed(items, key = { index, item -> item }) { index, item ->
            // Each item is placed in a Box to which we add:
            // - animateItemPlacement() for smooth animated movement
            // - an offset to visually move the item if it's being dragged
            // - pointerInput to detect drag gestures
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .animateItemPlacement() // Animate position changes
                    .offset {
                        // Only apply a vertical offset if this item is currently being dragged
                        if (draggedItemIndex == index) {
                            IntOffset(x = 0, y = dragOffsetY.roundToInt())
                        } else {
                            IntOffset.Zero
                        }
                    }
                    .shadow(elevation = 4.dp)
                    .background(Color.LightGray)
                    .height(itemHeight)
                    .pointerInput(index) {
                        detectDragGestures(
                            onDragStart = {
                                draggedItemIndex = index
                            },
                            onDragCancel = {
                                draggedItemIndex = null
                                dragOffsetY = 0f
                            },
                            onDragEnd = {
                                draggedItemIndex = null
                                dragOffsetY = 0f
                            },
                            onDrag = { change, dragAmount: Offset ->
                                change.consume() // Consume the gesture event
                                dragOffsetY += dragAmount.y

                                // Determine how many item heights the drag has moved
                                val offsetItems = (dragOffsetY / itemHeightPx).toInt()
                                // Calculate the target index, keeping within list bounds
                                val targetIndex = (index + offsetItems).coerceIn(0, items.size - 1)
                                // If the item has moved over to a new index, swap them
                                if (targetIndex != index) {
                                    items.swap(index, targetIndex)
                                    // Update the dragged item index and adjust the drag offset accordingly
                                    draggedItemIndex = targetIndex
                                    dragOffsetY -= offsetItems * itemHeightPx
                                }
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = item)
            }
        }
    }
}

// Extension function to swap two elements in a MutableList
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReorderableListTheme {
        ReorderableList()
    }
}
