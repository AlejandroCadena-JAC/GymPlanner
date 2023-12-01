package com.example.workouttracker.accountpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.Visibility

@Composable
fun SignInPage(){
    var showLoginPage by remember { mutableStateOf(true) }

    if (showLoginPage) {
        LoginPage(onSignUpClick = { showLoginPage = false })
    } else {
        SignUpPage(onLogInClick = { showLoginPage = true })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(onSignUpClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TITLE
        Text(
            text = "Log In",
            modifier = Modifier
                .padding(bottom = 20.dp)
        )

        // EMAIL INPUT
        TextField(
            value = email,
            onValueChange = { newText ->
                email = newText
            },
            label = { Text(text = "Enter your email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )

        // PASSWORD INPUT
        TextField(
            value = password,
            onValueChange = { newText ->
                password = newText
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text("Enter your Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            trailingIcon = {
                Checkbox(
                    checked = passwordVisibility,
                    onCheckedChange = { passwordVisibility = it }
                )
            }
        )

        // LOG IN BUTTON
        Button(
            onClick = {
                // Handle login logic here
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text("Log In")
        }

        // SIGN UP BUTTON
        Button(
            onClick = { onSignUpClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text("Sign Up")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpPage(onLogInClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }
    val error = remember { mutableStateOf<String?>(null) }

    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TITLE
        Text(
            text = "Create an Account",
            modifier = Modifier
                .padding(bottom = 20.dp)
        )

        // EMAIL INPUT
        TextField(
            value = email,
            onValueChange = { newText ->
                email = newText
                emailError = if (!isValidEmail(newText)) "Invalid email format" else null
            },
            label = { Text(text = "Enter your email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = emailError != null,
        )
        emailError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        // USERNAME INPUT
        TextField(
            value = username,
            onValueChange = { newText ->
                username = newText
                usernameError = if (newText.isBlank()) "Username cannot be blank" else null
            },
            label = { Text("Enter your Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = usernameError != null,
        )
        usernameError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        // PASSWORD INPUT
        TextField(
            value = password,
            onValueChange = { newText ->
                password = newText
                passwordError = if (newText.length < 6) "Password must be at least 6 characters" else null
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text("Enter your Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = passwordError != null,
            trailingIcon = {
                Checkbox(
                    checked = passwordVisibility,
                    onCheckedChange = { passwordVisibility = it }
                )
            }
        )
        passwordError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        // CONFIRM PASSWORD
        TextField(
            value = confirmPassword,
            onValueChange = { newText ->
                confirmPassword = newText
                confirmPasswordError = if (newText != password) "Passwords do not match" else null
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text("Enter your Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = confirmPasswordError != null,
        )
        confirmPasswordError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(8.dp))
        }

        // AGE BOX
        TextField(
            value = age,
            onValueChange = { newText ->
                age = newText
                ageError = if (newText.isBlank() || newText.toIntOrNull() == null || newText.toInt() < 13) "Age must be 13 or older" else null
            },
            label = { Text("Enter your Age") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isError = ageError != null,
        )
        ageError?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }
        // SUBMIT BUTTON
        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank() &&
                    username.isNotBlank() && confirmPassword.isNotBlank() && age.isNotBlank() &&
                    emailError == null && usernameError == null && passwordError == null &&
                    confirmPasswordError == null && ageError == null
                ) {
                    // Handle sign-in logic or navigate to Firebase authentication
                } else {
                    error.value = "Please fix the errors before signing in."
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            enabled = true // Always enabled, validation will handle the actual sign-in conditions
        ) {
            Text("Sign In")
        }

        error.value?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }

        // BACK TO SIGN IN BUTTON
        Button(
            onClick = { onLogInClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text("Back to Log In")
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("^\\S+@\\S+\\.\\S+\$")
    return email.matches(emailRegex)
}