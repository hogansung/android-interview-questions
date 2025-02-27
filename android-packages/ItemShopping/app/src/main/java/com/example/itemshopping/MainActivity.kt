package com.example.itemshopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.itemshopping.ui.theme.ItemShoppingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ItemShoppingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ItemShoppingApp()
                }
            }
        }
    }
}

/*
**Objective**

Implement a two‑screen app using Jetpack Compose Navigation. The first screen displays a list of
items. When an item is tapped, navigate to a detail screen that shows the item’s details and
includes a counter that the user can increment. Ensure that the detail screen’s state (the counter)
is preserved (for example, across configuration changes) by using a saveable state.
*/
@Composable
fun ItemShoppingApp() {
    // TODO: Create a NavController using rememberNavController().
    // TODO: Set up a NavHost with two routes: "list" and "detail/{itemId}".
}

@Composable
fun ListScreen(navController: NavController) {
    val items = List(20) { "Item ${it + 1}" }
    // TODO: Display the items in a LazyColumn.
    // TODO: When an item is clicked, navigate to "detail/{itemId}" (pass the item's identifier).
}

@Composable
fun DetailScreen(itemId: String) {
    // TODO: Display a header showing "Detail for <itemId>".
    // TODO: Create a counter state (use rememberSaveable) and an "Increment" button.
    // TODO: Display the current counter value.
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ItemShoppingTheme {
        ItemShoppingApp()
    }
}