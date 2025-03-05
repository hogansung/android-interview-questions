package com.example.registrationform

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
import androidx.compose.ui.unit.dp
import com.example.registrationform.ui.theme.RegistrationFormTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistrationFormTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegistrationForm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
Objective:
Build a registration form that validates user input and shows/hides a referral code field based on
a checkbox selection. The submit button should only be enabled when all required fields are valid,
and on submit, display a success message.
 */
@Composable
fun RegistrationForm(modifier: Modifier = Modifier) {
    // TODO: Create state variables for username, password, confirm password, and referral code.
    // TODO: Create a state variable for a checkbox that toggles the visibility of the referral code field.
    // TODO: Create a state variable for submission status.

    Column(modifier = Modifier.padding(16.dp)) {
        // TODO: Add OutlinedTextField for username.
        // TODO: Add OutlinedTextField for password.
        // TODO: Add OutlinedTextField for confirm password.

        // TODO: Add a Row containing a Checkbox and a label "I have a referral code".

        // TODO: If the checkbox is checked, display an OutlinedTextField for the referral code.

        // TODO: Add a Submit button that is enabled only if:
        //       - username is not blank,
        //       - password is not blank,
        //       - password matches confirm password,
        //       - and, if the referral code field is visible, it is not blank.

        // TODO: When submitted, display a success message.
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RegistrationFormTheme {
        RegistrationForm()
    }
}