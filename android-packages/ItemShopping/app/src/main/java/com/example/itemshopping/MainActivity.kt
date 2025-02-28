package com.example.itemshopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.itemshopping.ui.theme.ItemShoppingTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ItemShoppingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ItemShoppingApp(modifier = Modifier.padding(innerPadding))
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
fun ItemShoppingApp(modifier: Modifier = Modifier) {
    // TODO: Create a NavController using rememberNavController().
    val navController = rememberNavController()
    // TODO: Set up a NavHost with two routes: "list" and "detail/{itemId}".
    NavHost(navController = navController, startDestination = "list") {
        // Home Screen destination
        composable("list") {
            ListScreen(navController)
        }
        // Detail Screen destination
        composable("detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
            DetailScreen(itemId)
        }

    }
}

@Composable
fun ListScreen(navController: NavController) {
    val items = List(40) { "Item ${it + 1}" }
    // TODO: Display the items in a LazyColumn.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp, 48.dp)
    ){
        items(items) { item ->
            Text(
                text = item,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        // TODO: When an item is clicked, navigate to "detail/{itemId}" (pass the item's identifier).
                        navController.navigate("detail/${item}")
                    }
            )
        }
    }
}

@Composable
fun DetailScreen(itemId: String) {
    var counterValue by rememberSaveable  { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TODO: Display a header showing "Detail for <itemId>".
        Text(text = "Detail for $itemId",
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 36.sp)
        )
        Spacer(modifier = Modifier.height(48.dp))


        // TODO: Create a counter state (use rememberSaveable) and an "Increment" button.
        Button(onClick = { counterValue++ },
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)) {
            Text(text =  "Increment",
                    fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))


        // TODO: Display the current counter value.
        Text(text = "Current count: $counterValue",
            fontSize = 24.sp)
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ItemShoppingTheme {
        ItemShoppingApp()
//        DetailScreen("detail/{1}")
    }
}