package com.example.simpleformsubmission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simpleformsubmission.ui.theme.SimpleFormSubmissionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleFormSubmissionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserForm(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UserForm(modifier: Modifier = Modifier) {
    // TODO: Implement a simple form, with TextField components for user's name and email address.
    // TODO: Display a button labeled "Submit." The "Submit" button should only be enabled when both fields are not empty.
    // TODO: When the button is clicked, display the entered name and email address below the form.

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp, 64.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { newValue ->
                name = newValue;
                showResult = false
            },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { newValue ->
                email = newValue;
                showResult = false
            },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
            showResult = true
        },
            enabled = name.isNotBlank()  && email.isNotBlank() // Button is enabled only if inputText is not empty

        ) {
            Text("Submit")
        }

        if (showResult) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Name: $name")
            Text(text = "Email: $email")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserFormPreview() {
    SimpleFormSubmissionTheme {
        UserForm()
    }
}