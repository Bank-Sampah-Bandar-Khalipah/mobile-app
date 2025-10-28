package com.example.nasabahcompose.ui.admin.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val darkBlue = Color(0xFF0B1D51)
val lightBlue = Color(0xFFD6E3FF)
val lightGrayBg = Color(0xFFF5F5F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPickUpScreen(
    username: String,
    navController: NavController
) {
    var selectedTab by remember { mutableStateOf("Antar Sendiri") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Pengangkutan Sampah",
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = lightGrayBg,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = lightGrayBg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HistoryToggleButtons(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    HistoryItem()
                }
                item {
                    HistoryItem()
                }
            }
        }
    }
}


@Composable
fun HistoryToggleButtons(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val antarSelected = selectedTab == "Antar Sendiri"
        Button(
            onClick = { onTabSelected("Antar Sendiri") },
            modifier = Modifier.weight(1f),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (antarSelected) lightBlue else Color.White,
                contentColor = if (antarSelected) darkBlue else Color.Gray
            ),
            border = if (antarSelected) null else BorderStroke(1.dp, Color.Gray),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text("Antar Sendiri")
        }

        val jemputSelected = selectedTab == "Jemput Sampah"
        Button(
            onClick = { onTabSelected("Jemput Sampah") },
            modifier = Modifier.weight(1f),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (jemputSelected) lightBlue else Color.White,
                contentColor = if (jemputSelected) darkBlue else Color.Gray
            ),
            border = if (jemputSelected) null else BorderStroke(1.dp, Color.Gray),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text("Jemput Sampah")
        }
    }
}

@Composable
fun HistoryItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Berhasil",
            color = darkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                append("Dari lokasi ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Anda antar ke Tempat Penyetoran Sampah")
                }
            },
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = "Rabu, 30 Juli 2025",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "12:54",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "3.5 kg Botol Plastik; 2 kg Botol Plastik; 5.5 kg Kard....",
            color = Color.Gray,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "3 Item",
                color = darkBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = "•",
                color = darkBlue,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Rp 47.500",
                color = darkBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}