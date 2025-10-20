package com.example.nasabahcompose.ui.admin.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminUserScreen(
    username: String,
    navController: NavController,
    onLogout: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var userRole by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch data dari Firebase
    LaunchedEffect(Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            val userRef = FirebaseDatabase.getInstance()
                .getReference("user")
                .child(uid)

            userRef.get().addOnSuccessListener { snapshot ->
                name = snapshot.child("username").getValue(String::class.java) ?: ""
                userRole = snapshot.child("role").getValue(String::class.java) ?: ""
                isLoading = false
            }.addOnFailureListener {
                isLoading = false
            }
        }
    }

    Scaffold { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF0B1D51))
            }
        } else {
            Column {
                Text(
                    text = "Profil",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF0B1D51)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = name.firstOrNull()?.uppercase() ?: "A",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 16.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    text = userRole,
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            },
                            shape = CircleShape,
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color.White
                            ),
                            border = BorderStroke(1.dp, Color.Gray),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }

                Text(
                    text = "Akun Anda",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    onClick = {}
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = Color(0xFF0B1D51),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Ubah Profil",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 16.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    onClick = {}
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = null,
                            tint = Color(0xFF0B1D51),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Ubah Kata Sandi",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 16.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }

                Text(
                    text = "Pengaturan Tambahan",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    onClick = {}
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.QuestionMark,
                            contentDescription = null,
                            tint = Color(0xFF0B1D51),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Kebijakan Privasi",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 16.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    onClick = { onLogout() }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = null,
                            tint = Color(0xFF0B1D51),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Keluar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 16.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }

            }
        }
    }
}