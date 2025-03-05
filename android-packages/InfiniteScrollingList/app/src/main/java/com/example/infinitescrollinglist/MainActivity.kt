package com.example.infinitescrollinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.infinitescrollinglist.ui.theme.InfiniteScrollingListTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfiniteScrollingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InfiniteScrollingList(modifier = Modifier.padding((innerPadding)))
                }
            }
        }
    }
}

/*
Objective:
Implement an infinite scrolling list using a LazyColumn. Begin with an initial page of data; when
the user scrolls near the bottom, simulate loading the next page (with a delay) and append it to
the list. Display a loading indicator during the data fetch.
*/
@Composable
fun InfiniteScrollingList(modifier: Modifier = Modifier) {
    // Create a state variable for a list of items (e.g., List<Int>) and initialize with the first page.
    val itemsList = remember { mutableStateListOf<Int>().apply { addAll(1..30) } }
    // Create a state variable for loading status.
    var isLoading by remember { mutableStateOf(false) }
    // Create a LazyListState for the LazyColumn.
    val lazyListStatus = rememberLazyListState()
    // Use LaunchedEffect to monitor scroll position.
    // When the list scrolls near the bottom, simulate a delay (e.g., 2000ms) and load the next page of items.
    // 監控滾動狀態，當最後一個可見項目接近列表末尾時載入下一頁
    LaunchedEffect(lazyListStatus) {
        snapshotFlow { lazyListStatus.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .map { it?: 0 }
            .distinctUntilChanged()
            .collect{lastVisibleItemIndex ->
                // 當最後一個可見項目超過列表末尾前 3 個項目時觸發
                if (lastVisibleItemIndex >= itemsList.size - 3 && !isLoading) {
                    isLoading = true
                    delay(2000)
                    // 載入下一頁資料，例如新增接下來 20 個數字
                    val nextItems = (itemsList.size until itemsList.size + 30).toList()
                    itemsList.addAll(nextItems)
                    isLoading = false
                }
            }
    }

    LazyColumn(state = lazyListStatus, modifier = Modifier.fillMaxSize().padding(top = 48.dp)) {
        // Display the list items.
        items(itemsList) {item ->
            Text(text = "$item", modifier = Modifier.padding(24.dp, 8.dp))
        }
        // If loading, display a CircularProgressIndicator at the bottom.
        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InfiniteScrollingListTheme {
        InfiniteScrollingList()
    }
}