package com.example.lazylistwithclickableitems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazylistwithclickableitems.ui.theme.LazyListWithClickableItemsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyListWithClickableItemsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyListWithClickableItems()
                }
            }
        }
    }
}

@Composable
fun LazyListWithClickableItems() {
    // TODO: Create a LazyColumn that displays a list of items, e.g. "Item 1", "Item 2", ..., "Item 10".
    // TODO: When an item is clicked, a Snackbar or a simple Text should show the clicked item's name.

    val items = List(10) { "Item ${it + 1}" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyListWithClickableItemsPreview() {
    LazyListWithClickableItemsTheme {
        LazyListWithClickableItems()
    }
}