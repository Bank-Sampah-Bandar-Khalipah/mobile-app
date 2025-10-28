package com.example.nasabahcompose.ui.admin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.DesktopWindows
import androidx.compose.material.icons.outlined.Liquor
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.NumberFormat
import java.util.Locale

data class WasteItem(
    val id: String,
    val name: String,
    val pricePerKg: Double,
    val icon: ImageVector,
    var quantity: Double = 0.0
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAddScreen(
    username: String,
    navController: NavController
) {
    val initialItems = remember {
        mutableStateListOf(
            WasteItem("kertas", "Kertas", 1000.0, Icons.AutoMirrored.Outlined.Article),
            WasteItem("plastik", "Plastik", 1000.0, Icons.Outlined.Liquor),
            WasteItem("elektronik", "Elektronik", 1000.0, Icons.Outlined.DesktopWindows),
            WasteItem("besi", "Besi", 1000.0, Icons.Outlined.Shield),
            WasteItem("botolkaca", "Botol Kaca", 1000.0, Icons.Outlined.Liquor),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Jenis Sampah",
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
        containerColor = lightGrayBg,
        bottomBar = {
            Column(modifier = Modifier.background(lightGrayBg)) {
                Button(
                    onClick = { navController.navigate("admin_gathering") },
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 32.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkBlue,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Selanjutnya",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(initialItems) { item ->
                WasteItemCard(item = item)
            }
        }
    }
}

@Composable
fun WasteItemCard(item: WasteItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(lightBlue, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        tint = darkBlue,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = item.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        text = "${formatRupiah(item.pricePerKg)}/kg",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(
                    onClick = { if (item.quantity >= 0.5) item.quantity -= 0.5 },
                    modifier = Modifier
                        .size(36.dp)
                        .background(lightBlue, CircleShape)
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease", tint = darkBlue)
                }

                Text(
                    text = "${String.format(Locale.US, "%.1f", item.quantity)} kg",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.width(60.dp),
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = { item.quantity += 0.5 },
                    modifier = Modifier
                        .size(36.dp)
                        .background(lightBlue, CircleShape)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase", tint = darkBlue)
                }
            }
        }
    }
}

fun formatRupiah(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    formatter.maximumFractionDigits = 0
    return formatter.format(amount)
}