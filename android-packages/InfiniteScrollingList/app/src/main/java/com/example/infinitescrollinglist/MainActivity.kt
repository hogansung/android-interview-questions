package com.example.infinitescrollinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.infinitescrollinglist.ui.theme.InfiniteScrollingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfiniteScrollingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InfiniteScrollingList()
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
fun InfiniteScrollingList() {
    // TODO: Create a state variable for a list of items (e.g., List<Int>) and initialize with the first page.
    // TODO: Create a state variable for loading status.
    // TODO: Create a LazyListState for the LazyColumn.

    // TODO: Use LaunchedEffect to monitor scroll position.
    //       When the list scrolls near the bottom, simulate a delay (e.g., 2000ms) and load the next page of items.

    LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {
        // TODO: Display the list items.
        // TODO: If loading, display a CircularProgressIndicator at the bottom.
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InfiniteScrollingListTheme {
        InfiniteScrollingList()
    }
}