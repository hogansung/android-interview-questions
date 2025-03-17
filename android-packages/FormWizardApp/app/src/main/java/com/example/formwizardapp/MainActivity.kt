package com.example.formwizardapp

import android.os.Bundle
import android.os.Parcelable
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
import androidx.compose.foundation.layout.Row
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormData(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = ""
) : Parcelable

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

@Composable
fun FormWizardApp(modifier: Modifier = Modifier) {
    // Create a NavController and set up a NavHost with routes for "stepOne", "stepTwo", "stepThree", and "summary".
    // Maintain a state variable for FormData (and update it between screens).
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

    // Display input fields for first name and last name.
    // Provide a "Next" button to navigate to "stepTwo", updating the FormData.
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
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
    // Display input fields for email and phone.
    // Provide "Previous" and "Next" buttons to navigate back or forward.
    var email by remember { mutableStateOf(formData.email) }
    var phone by remember { mutableStateOf(formData.phone) }

    Column(
        modifier = Modifier.padding(16.dp)
    )  {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate("stepOne")
                }) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.weight(1f)) // 分配剩餘空間，將 Next 按鈕推到右側
            Button(
                onClick = {
                    updateFormData(formData.copy(email = email, phone = phone))
                    navController.navigate("stepThree")
                }){
                Text("Next")
            }
        }
    }
}

@Composable
fun StepThreeScreen(navController: NavController, formData: FormData, updateFormData: (FormData) -> Unit) {
    // Display an input field for address.
    // Provide "Previous" and "Submit" buttons. On submit, navigate to "summary".
    var address by remember { mutableStateOf(formData.address) }

    Column(
        modifier = Modifier.padding(16.dp)
    )  {
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate("stepTwo")
                }) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.weight(1f)) // 分配剩餘空間，將 Next 按鈕推到右側
            Button(
                onClick = {
                    updateFormData(formData.copy(address = address))
                    navController.navigate("summary")
                }){
                Text("Next")
            }
        }
    }
}

@Composable
fun SummaryScreen(formData: FormData) {
    // Display a summary of the collected form data.
    Column(
        modifier = Modifier.padding(16.dp)
    )  {
        Text(text = "firstName: ${formData.firstName}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "lastName: ${formData.lastName}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "email: ${formData.email}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "phone: ${formData.phone}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "address: ${formData.address}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FormWizardAppTheme {
        FormWizardApp()
    }
}