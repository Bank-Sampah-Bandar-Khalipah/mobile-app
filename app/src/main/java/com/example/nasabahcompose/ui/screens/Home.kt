package com.example.nasabahcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.Trash
import com.example.nasabahcompose.ui.components.*

@Composable
fun HomeScreen(trashList: List<Trash>) {
    Scaffold(
        bottomBar = { MyNavbarBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F2F2))
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            HomeHeader(name = "Alex", points = "30.000")
            Spacer(modifier = Modifier.height(16.dp))
            PickupDeliverySection()
            Spacer(modifier = Modifier.height(16.dp))
            FeatureSection()
            Spacer(modifier = Modifier.height(16.dp))
            TrashPriceSection(trashList = trashList)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val dummyData = listOf(
        Trash("Tutup Botol", 1000, R.drawable.sampah, "Plastik"),
        Trash("Kertas", 800, R.drawable.sampah, "Kertas"),
        Trash("Kardus", 1200, R.drawable.sampah, "Kardus")
    )

    HomeScreen(trashList = dummyData)
}
