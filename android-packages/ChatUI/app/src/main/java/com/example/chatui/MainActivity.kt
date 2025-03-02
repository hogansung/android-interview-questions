package com.example.chatui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatui.ui.theme.ChatUITheme
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList

// Create a Saver for SnapshotStateList<String> using listSaver
val messageListSaver = listSaver<SnapshotStateList<String>, String>(
    save = { stateList -> stateList.toList() },
    restore = { savedList -> mutableStateListOf(*savedList.toTypedArray()) }
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
**Objective**
Implement a chat interface using Jetpack Compose that supports the following:

A scrollable list of messages displayed in a LazyColumn.
An input field with a send button to add new messages.
When a new message is sent, it appears with a fade‑in (and slide‑in) animation.
The list automatically scrolls to the newest message.
*/
@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    // TODO: Create a state variable to hold the list of messages (e.g., a MutableList<String>).
    var messageList = rememberSaveable(saver = messageListSaver) { mutableStateListOf<String>() }
    // TODO: Create a state for the current text input.
    var currText by rememberSaveable { mutableStateOf("") }
    // TODO: Remember a LazyListState for the LazyColumn.
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // TODO: Implement a LazyColumn that displays the messages.
        // Each message should animate when it appears.
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.End, // 使每个 item 都右对齐
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp, 56.dp)
        ) {
            items(messageList) { item ->
                Card(
                    modifier = Modifier
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    ) {
                    Box(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = item,
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }

        // TODO: At the bottom, implement a Row containing a TextField and a Button labeled "Send".
        // On click, if the input is non-empty, add the message to the list and clear the input.
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = currText,
                onValueChange = { newText ->
                    currText = newText
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                placeholder = {
                    Text("Type here")
                }
            )

            Button(onClick = {
                messageList.add(currText)
                currText = ""
            },
                enabled = currText.isNotEmpty(),
                modifier = Modifier
                    .padding(top = 12.dp,end = 8.dp)
                ) {
                Text( "send")
            }
        }
    }

    // TODO: Use LaunchedEffect to scroll the LazyColumn to the latest message when one is added.
    // LaunchedEffect that listens to changes in messageList.size
    LaunchedEffect(messageList.size) {
        if (messageList.isNotEmpty()) {
            // Scroll to the last message index when a new message is added
            listState.animateScrollToItem(messageList.lastIndex)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatUITheme {
        ChatScreen()
    }
}