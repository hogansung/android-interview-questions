package com.example.formwizardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val navController = rememberNavController()
    val formDataState = rememberSaveable { mutableStateOf(FormData()) }
    NavHost(
        navController = navController,
        startDestination = "stepOne",
        modifier = modifier
    ) {
        composable("stepOne") {
            StepOneScreen(
                navController = navController,
                formData = formDataState.value,
                updateFormData = { formDataState.value = it }
            )
        }
        composable("stepTwo") {
            StepTwoScreen(
                navController = navController,
                formData = formDataState.value,
                updateFormData = { formDataState.value = it }
            )
        }
        composable("stepThree") {
            StepThreeScreen(
                navController = navController,
                formData = formDataState.value,
                updateFormData = { formDataState.value = it }
            )
        }
        composable("summary") {
            SummaryScreen(formData = formDataState.value)
        }
    }
}

@Composable
fun StepOneScreen(navController: NavController, formData: FormData, updateFormData: (FormData) -> Unit) {
    var firstName by remember { mutableStateOf(formData.firstName) }
    var lastName by remember { mutableStateOf(formData.lastName) }

    // TODO: Display input fields for first name and last name.
    // TODO: Provide a "Next" button to navigate to "stepTwo", updating the FormData.
    Column {
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            updateFormData(formData.copy(firstName = firstName, lastName = lastName))
            navController.navigate("stepTwo")
        },
            modifier = Modifier.align(Alignment.End)) {Text("Next") }
    }
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