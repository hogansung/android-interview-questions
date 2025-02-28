package com.example.chatui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatui.ui.theme.ChatUITheme

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
    // TODO: Create a state for the current text input.
    // TODO: Remember a LazyListState for the LazyColumn.

    Column(modifier = Modifier.fillMaxSize()) {
        // TODO: Implement a LazyColumn that displays the messages.
        // Each message should animate when it appears.

        // TODO: At the bottom, implement a Row containing a TextField and a Button labeled "Send".
        // On click, if the input is non-empty, add the message to the list and clear the input.
    }

    // TODO: Use LaunchedEffect to scroll the LazyColumn to the latest message when one is added.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatUITheme {
        ChatScreen()
    }
}