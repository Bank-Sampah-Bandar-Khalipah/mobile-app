package com.example.nasabahcompose.ui.nasabah.screens.point

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nasabahcompose.R
import com.example.nasabahcompose.ui.nasabah.components.commons.CommonHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PencairanDanaScreen(navController: NavController) {
    val navy = Color(0xFF0B1F5C)
    var nominal by rememberSaveable { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBFAFA))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header menggunakan CommonHeader
            CommonHeader(
                title = "Pencairan Dana",
                navController = navController,
                backgroundColor = Color(0xFFFBFAFA)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Kartu poin atas (mini)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(84.dp)
                    .shadow(6.dp, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // left pill (ikon + teks poin)
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F9FF)),
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(40.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 18.dp)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.noto_coin),
                                contentDescription = "Coin",
                                modifier = Modifier.size(18.dp),
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "30.000 Poin",
                                fontWeight = FontWeight.SemiBold,
                                color = navy
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // right icon (wallet)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_money),
                        contentDescription = "Wallet",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(thickness = 1.dp, color = Color(0xFFE9E9E9))

            Spacer(modifier = Modifier.height(24.dp))

            // Total Saldo card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(84.dp)
                    .shadow(6.dp, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Total Saldo", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.weight(1f))
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F9FF)),
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(40.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 18.dp)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.noto_coin),
                                contentDescription = "Coin",
                                modifier = Modifier.size(18.dp),
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Rp 30.000", fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Pill title mengambang (Permintaan Pencairan Dana) - teks center
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = navy),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Permintaan Pencairan Dana",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Input nominal: TextField dengan teks/placeholder tengah horizontal dan center vertical dalam card area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                TextField(
                    value = nominal,
                    onValueChange = { nominal = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(3.dp, RoundedCornerShape(12.dp)),
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center),
                    placeholder = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Masukkan nominal yang ingin dicairkan",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Color(0xFF9A9A9A)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Tombol Cairkan Dana (tengah)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 72.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /* TODO: implement action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF565D6B)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Cairkan Dana", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, heightDp = 800)
@Composable
fun PencairanDanaPreview() {
    val nav = rememberNavController()
    PencairanDanaScreen(navController = nav)
}