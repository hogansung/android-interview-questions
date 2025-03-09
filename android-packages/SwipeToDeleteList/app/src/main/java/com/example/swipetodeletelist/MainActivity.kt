package com.example.swipetodeletelist

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
import com.example.swipetodeletelist.ui.theme.SwipeToDeleteListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwipeToDeleteListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SwipeToDeleteList()
                }
            }
        }
    }
}

/*
Objective:
Implement a list of items where each item can be swiped away to delete it. Use Compose’s
swipe-to-dismiss functionality and animate the removal.
*/
@Composable
fun SwipeToDeleteList() {
    // TODO: Create a state variable for a list of items.
    // TODO: Display the items in a LazyColumn.
    // TODO: For each item, implement swipe-to-dismiss functionality:
    //       - Allow the item to be swiped left or right.
    //       - On dismiss, remove the item from the list.
    //       - Animate the removal of the item.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwipeToDeleteListTheme {
        SwipeToDeleteList()
    }
}