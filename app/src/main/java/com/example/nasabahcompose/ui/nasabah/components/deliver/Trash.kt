package com.example.nasabahcompose.ui.nasabah.components.deliver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun JenisSampahCard(
    name: String,
    iconRes: Int,
    pricePerKg: Int,
    modifier: Modifier = Modifier,
    onWeightChanged: (Float, Int) -> Unit = { _, _ -> }
) {
    var weight by remember { mutableStateOf(0f) }
    var showDialog by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }

    val totalPrice = (weight * pricePerKg).toInt()

    // Update parent saat weight berubah
    LaunchedEffect(weight) {
        onWeightChanged(weight, totalPrice)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
            .border(0.5.dp, Color(0xFFE0E0E0), RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon dan Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFEAF3FF), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = name,
                        tint = Color(0xFF004AAD),
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF004AAD)
                    )
                    Text(
                        text = "Rp ${pricePerKg}/kg",
                        fontSize = 12.sp,
                        color = Color(0xFF666666)
                    )
                }
            }

            // Weight Control
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Tombol Minus
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(Color.Transparent, CircleShape)
                        .border(1.dp, Color(0xFF004AAD), CircleShape)
                        .clickable {
                            if (weight > 0) weight = (weight - 0.5f).coerceAtLeast(0f)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "−",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF004AAD)
                    )
                }

                // Weight Display - Clickable untuk edit
                Box(
                    modifier = Modifier
                        .widthIn(min = 60.dp)
                        .clickable {
                            inputText = if (weight > 0) weight.toString() else ""
                            showDialog = true
                        }
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${String.format("%.1f", weight)} kg",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF004AAD)
                    )
                }

                // Tombol Plus
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(Color.Transparent, CircleShape)
                        .border(1.dp, Color(0xFF004AAD), CircleShape)
                        .clickable { weight += 0.5f },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF004AAD)
                    )
                }
            }
        }
    }

    // Dialog untuk input manual
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Masukkan Berat $name",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    // Input Field
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicTextField(
                            value = inputText,
                            onValueChange = { newValue ->
                                // Hanya terima angka dan titik desimal
                                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d{0,2}$"))) {
                                    inputText = newValue
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            textStyle = TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = Color(0xFF1976D2)
                            ),
                            singleLine = true,
                            decorationBox = { innerTextField ->
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    if (inputText.isEmpty()) {
                                        Text(
                                            text = "0.0",
                                            fontSize = 36.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFBDBDBD)
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }

                    Text(
                        text = "kg",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Tombol Batal
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .background(Color(0xFFE0E0E0), RoundedCornerShape(25.dp))
                                .clickable {
                                    showDialog = false
                                    inputText = ""
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Batal",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF424242)
                            )
                        }

                        // Tombol Simpan
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .background(Color(0xFF1976D2), RoundedCornerShape(25.dp))
                                .clickable {
                                    val newWeight = inputText.toFloatOrNull() ?: 0f
                                    weight = newWeight
                                    showDialog = false
                                    inputText = ""
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Simpan",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}