package com.example.registrationform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    // Create state variables for username, password, confirm password, and referral code.
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var referralCode by remember { mutableStateOf("") }

    // Create a state variable for a checkbox that toggles the visibility of the referral code field.
    var isCheckboxClicked by remember { mutableStateOf(false) }

    // Create a state variable for submission status.
    var isSubmitSuc by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp, 48.dp, 16.dp, 16.dp)) {
        // Add OutlinedTextField for username.
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Add OutlinedTextField for password.
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Add OutlinedTextField for confirm password.
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Add a Row containing a Checkbox and a label "I have a referral code".
        Row {
            Checkbox(
                checked = isCheckboxClicked,
                onCheckedChange = {isCheckboxClicked = !isCheckboxClicked}
            )
            Text(
                text = "I have a referral code",
                modifier = Modifier.padding(8.dp, 12.dp)
                )
        }

        // If the checkbox is checked, display an OutlinedTextField for the referral code.
        if (isCheckboxClicked) {
            OutlinedTextField(
                value = referralCode,
                onValueChange = { referralCode = it },
                label = { Text("ReferralCode") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        // Add a Submit button that is enabled only if:
        //       - username is not blank,
        //       - password is not blank,
        //       - password matches confirm password,
        //       - and, if the referral code field is visible, it is not blank.
        Button(
            onClick = {isSubmitSuc = true},
            modifier = Modifier
                .fillMaxWidth()
                .padding(68.dp, 0.dp, 68.dp),
            enabled = username != "" &&
                    password != "" &&
                    password == confirmPassword &&
                    ((isCheckboxClicked && referralCode != "") || !isCheckboxClicked)

        ) {
            Text("Submit")
        }

        // When submitted, display a success message.
        if (isSubmitSuc) {
            Text(
                text = "Submission Successful!",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RegistrationFormTheme {
        RegistrationForm()
    }
}