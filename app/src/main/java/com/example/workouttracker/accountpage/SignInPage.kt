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

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInPage() {
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
            text = "App Title",
            modifier = Modifier.padding(bottom = 20.dp)
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
                // Handle sign-in logic or navigate to Firebase authentication
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            enabled = email.isNotBlank() && password.isNotBlank() &&
                    username.isNotBlank() && confirmPassword.isNotBlank() && age.isNotBlank()
        ) {
            Text("Sign In")
        }

        error.value?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(vertical = 5.dp))
        }
    }
}