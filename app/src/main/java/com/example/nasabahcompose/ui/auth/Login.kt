package com.example.nasabahcompose.ui.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

data class UserData(
    val username: String = "",
    val role: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: (UserData) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = remember { FocusRequester() }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val screenHeight = maxHeight
        val isSmallScreen = screenHeight < 600.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = if (isSmallScreen) 24.dp else 32.dp)
                .padding(vertical = if (isSmallScreen) 16.dp else 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selamat Datang !",
                fontWeight = FontWeight.Bold,
                style = if (isSmallScreen)
                    MaterialTheme.typography.headlineMedium
                else
                    MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF0B1D51),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(if (isSmallScreen) 24.dp else 40.dp))

            Text(
                text = "Masuk ke Akun Anda",
                style = if (isSmallScreen)
                    MaterialTheme.typography.bodyLarge
                else
                    MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF0B1D51),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(if (isSmallScreen) 16.dp else 24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", fontSize = if (isSmallScreen) 14.sp else 16.sp) },
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                enabled = !isLoading,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = if (isSmallScreen) 14.sp else 16.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = if (isSmallScreen) 52.dp else 56.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF0B1D51),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color(0xFF0B1D51),
                    focusedLabelColor = Color(0xFF0B1D51),
                    disabledBorderColor = Color.LightGray,
                    disabledTextColor = Color.Gray,
                    disabledLabelColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(if (isSmallScreen) 12.dp else 16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", fontSize = if (isSmallScreen) 14.sp else 16.sp) },
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                enabled = !isLoading,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (isPasswordVisible)
                        Icons.Default.Visibility
                    else Icons.Default.VisibilityOff

                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible },
                        enabled = !isLoading,
                        modifier = Modifier.size(if (isSmallScreen) 20.dp else 24.dp)
                    ) {
                        Icon(
                            imageVector = image,
                            contentDescription = null,
                            modifier = Modifier.size(if (isSmallScreen) 20.dp else 24.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        if (email.isNotBlank() && password.isNotBlank() && !isLoading) {
                            performLogin(email, password, context, onLoginSuccess) { isLoading = it }
                        }
                    }
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = if (isSmallScreen) 14.sp else 16.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = if (isSmallScreen) 52.dp else 56.dp)
                    .focusRequester(passwordFocusRequester),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF0B1D51),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color(0xFF0B1D51),
                    focusedLabelColor = Color(0xFF0B1D51),
                    disabledBorderColor = Color.LightGray,
                    disabledTextColor = Color.Gray,
                    disabledLabelColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(if (isSmallScreen) 24.dp else 32.dp))

            Button(
                onClick = {
                    focusManager.clearFocus()
                    if (email.isNotBlank() && password.isNotBlank()) {
                        performLogin(email, password, context, onLoginSuccess) { isLoading = it }
                    } else {
                        Toast.makeText(
                            context,
                            "Email dan Password tidak boleh kosong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                enabled = !isLoading && email.isNotBlank() && password.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0B1D51),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF0B1D51).copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = if (isSmallScreen) 48.dp else 56.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(if (isSmallScreen) 20.dp else 24.dp)
                    )
                } else {
                    Text(
                        text = "Login",
                        fontSize = if (isSmallScreen) 14.sp else 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

private fun performLogin(
    email: String,
    password: String,
    context: android.content.Context,
    onLoginSuccess: (UserData) -> Unit,
    setLoading: (Boolean) -> Unit
) {
    setLoading(true)

    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = FirebaseAuth.getInstance().currentUser?.uid
                if (uid != null) {
                    val userRef = FirebaseDatabase.getInstance()
                        .getReference("user")
                        .child(uid)

                    userRef.get()
                        .addOnSuccessListener { snapshot ->
                            setLoading(false)
                            val username = snapshot.child("username").getValue(String::class.java) ?: "User"
                            val role = snapshot.child("role").getValue(String::class.java) ?: "user"

                            onLoginSuccess(UserData(username, role))
                        }
                        .addOnFailureListener { exception ->
                            setLoading(false)
                            Toast.makeText(
                                context,
                                "Gagal memuat data: ${exception.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Default ke user jika gagal
                            onLoginSuccess(UserData("User", "user"))
                        }
                } else {
                    setLoading(false)
                    Toast.makeText(
                        context,
                        "Error: User ID tidak ditemukan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                setLoading(false)
                val errorMessage = when {
                    task.exception?.message?.contains("no user record") == true ->
                        "Email tidak terdaftar"
                    task.exception?.message?.contains("password is invalid") == true ->
                        "Password salah"
                    task.exception?.message?.contains("network") == true ->
                        "Koneksi internet bermasalah"
                    else -> "Login gagal: ${task.exception?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
}