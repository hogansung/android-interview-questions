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
                    UserForm()
                }
            }
        }
    }
}

@Composable
fun UserForm() {
    var name = ""
    var email = ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { }) {
            Text("Submit")
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