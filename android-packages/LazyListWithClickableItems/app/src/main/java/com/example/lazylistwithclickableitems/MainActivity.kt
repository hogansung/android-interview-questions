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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            LazyListWithClickableItemsTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    LazyListWithClickableItems(modifier = Modifier.padding(innerPadding))
//                }
//            }
            LazyListWithClickableItemsTheme {
                // 1. Create a SnackbarHostState
                val snackbarHostState = remember { SnackbarHostState() }
                // 2. Create a CoroutineScope
                val scope = rememberCoroutineScope()

                // 3. Top-level Scaffold with snackbarHost
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { innerPadding ->
                    LazyListWithClickableItems(
                        modifier = Modifier.padding(innerPadding),
                        // 4. Pass a callback for item clicks
                        onItemClick = { item ->
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Clicked: $item",
//                                    duration = androidx.compose.material3.SnackbarDuration.Short
                                    duration = androidx.compose.material3.SnackbarDuration.Indefinite

                                )
                            }
                            scope.launch {
                                // 第二個協程：等待指定的時間後，關閉 Snackbar
                                // 延遲 1.5 秒後關閉 Snackbar（可根據需求調整時間）( Material3 預設 Short 時間約 4 秒)
                                kotlinx.coroutines.delay(1500L)
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LazyListWithClickableItems(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    // TODO: Create a LazyColumn that displays a list of items, e.g. "Item 1", "Item 2", ..., "Item 10".
    // TODO: When an item is clicked, a Snackbar or a simple Text should show the clicked item's name.

    val items = List(10) { "Item ${it + 1}" }
//    var clickedItem by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp, 48.dp)
    ) {
//        LazyColumn {
//            items(items) { item ->
//                Text(
//                    text = item,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
//                            clickedItem = item  // Update the state variable
//                            onItemClick(item)    // Call the callback (if needed)
//                        }
//
//                        .padding(8.dp)
//                )
//            }
//        }
//        clickedItem?.let {
//            Text(
//                text = "You clicked: $it",
//                modifier = Modifier.padding(top = 16.dp)
//            )
//        }
        LazyColumn(modifier = modifier) {
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item)  // Trigger the callback passed down from parent
                        }
                        .padding(16.dp)
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