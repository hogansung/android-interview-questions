package com.example.reorderablelist

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
import com.example.reorderablelist.ui.theme.ReorderableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReorderableListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ReorderableList()
                }
            }
        }
    }
}

/*
Objective:
Build a list of items that the user can reorder by dragging and dropping. Use Compose’s gesture
detectors along with animated item placement to update the list order interactively.
*/

@Composable
fun ReorderableList() {
    // TODO: Create a stateful list of items (e.g., List<String>).
    // TODO: Display the items in a LazyColumn.
    // TODO: Implement drag-and-drop gestures to allow reordering.
    // TODO: Animate the movement of items when their position changes.
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReorderableListTheme {
        ReorderableList()
    }
}