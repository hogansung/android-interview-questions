package com.example.formwizardapp

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
import androidx.navigation.NavController
import com.example.formwizardapp.ui.theme.FormWizardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormWizardAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormWizardApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
Objective:
Create a multi‑step form wizard spanning three screens plus a summary screen. Each step collects
part of the user’s data. Navigation should be handled via Compose Navigation, and the form data
must be preserved across screens (using, for example, a shared state via a data class and
rememberSaveable).
*/

data class FormData(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = ""
)

@Composable
fun FormWizardApp(modifier: Modifier = Modifier) {
    // TODO: Create a NavController and set up a NavHost with routes for "stepOne", "stepTwo", "stepThree", and "summary".
    // TODO: Maintain a state variable for FormData (and update it between screens).
}

@Composable
fun StepOneScreen(navController: NavController, formData: FormData, updateFormData: (FormData) -> Unit) {
    // TODO: Display input fields for first name and last name.
    // TODO: Provide a "Next" button to navigate to "stepTwo", updating the FormData.
}

@Composable
fun StepTwoScreen(navController: NavController, formData: FormData, updateFormData: (FormData) -> Unit) {
    // TODO: Display input fields for email and phone.
    // TODO: Provide "Previous" and "Next" buttons to navigate back or forward.
}

@Composable
fun StepThreeScreen(navController: NavController, formData: FormData, updateFormData: (FormData) -> Unit) {
    // TODO: Display an input field for address.
    // TODO: Provide "Previous" and "Submit" buttons. On submit, navigate to "summary".
}

@Composable
fun SummaryScreen(formData: FormData) {
    // TODO: Display a summary of the collected form data.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FormWizardAppTheme {
        FormWizardApp()
    }
}