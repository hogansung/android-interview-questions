package com.example.swipetodeletelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swipetodeletelist.ui.theme.SwipeToDeleteListTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

// A Saver that converts SnapshotStateList<Int> to/from a plain List<Int>
val IntListSaver = listSaver<SnapshotStateList<Int>, Int>(
    save = { it.toList() },
    restore = { savedInts ->
        mutableStateListOf<Int>().apply { addAll(savedInts) }
    }
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwipeToDeleteListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SwipeToDeleteList(Modifier.padding(innerPadding))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteList(modifier: Modifier = Modifier) {
    // Create a state variable for a list of items.
//    var itemsList = remember { mutableStateListOf<Int>().apply {addAll(1..40)} }
    val itemsList = rememberSaveable(saver = IntListSaver) {
        mutableStateListOf<Int>().apply { addAll(1..40) }
    }

    // Display the items in a LazyColumn.
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 48.dp, 16.dp, 16.dp)
    ) {
        items(
            items = itemsList,
            key = {it}
        ) { item ->
            SwipeToDismissContainer(
                item = "$item",
                onDelete ={ itemsList.remove(item) },
            )
            {
                Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    Text(text = "$item")
                }
            }
        }

    }
}

// For each item, implement swipe-to-dismiss functionality:
//       - Allow the item to be swiped left or right.
//       - On dismiss, remove the item from the list.
//       - Animate the removal of the item.
@Composable
fun SwipeToDismissContainer(
    item: String,
    onDelete: (String) -> Unit,
    content: @Composable (String) -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { newValue ->
            if(newValue == SwipeToDismissBoxValue.StartToEnd ||
                newValue == SwipeToDismissBoxValue.EndToStart){
                onDelete(item)
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier,
        backgroundContent = {
            if (dismissState.dismissDirection.name == SwipeToDismissBoxValue.StartToEnd.name ||
                dismissState.dismissDirection.name == SwipeToDismissBoxValue.EndToStart.name) {
                Row(modifier = Modifier.fillMaxSize().background(Color.Red),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "delete")
                }
            }
        },
        content = {content(item)}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwipeToDeleteListTheme {
        SwipeToDeleteList()
    }
}