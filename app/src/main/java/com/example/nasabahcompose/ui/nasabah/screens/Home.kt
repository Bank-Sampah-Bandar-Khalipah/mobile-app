package com.example.nasabahcompose.ui.nasabah.screens

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.Trash
import com.example.nasabahcompose.ui.nasabah.components.home.FeatureSection
import com.example.nasabahcompose.ui.nasabah.components.home.HomeHeader
import com.example.nasabahcompose.ui.nasabah.components.home.PickupDeliverySection
import com.example.nasabahcompose.ui.nasabah.components.home.TrashPriceSection
import com.example.nasabahcompose.ui.nasabah.components.commons.MyNavbarBar

@Composable
fun HomeScreen(username: String, navController: NavHostController) {
    Scaffold(
        bottomBar = { MyNavbarBar(navController = navController, username = username) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F2F2))
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Home Header
            HomeHeader(
                name = username,
                points = "30.000",
                onRedeemClick = { navController.navigate("pencairan") }
            )

            // PickupDeliverySection
            PickupDeliverySection(navController = navController)

            Spacer(modifier = Modifier.height(16.dp))

            // FeatureSection - FULL WIDTH dengan internal contentPadding
            FeatureSection(navController = navController)

            Spacer(modifier = Modifier.height(16.dp))

            // TrashPriceSection - FULL WIDTH dengan internal contentPadding
            TrashPriceSection(
                navController = navController,
                trashList = listOf(
                    Trash("Botol Plastik", 2000, R.drawable.sampah, "Plastik"),
                    Trash("Kertas", 1000, R.drawable.buku, "Kertas"),
                    Trash("Kardus", 1500, R.drawable.kardus2, "Kardus")
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(username = "Yusuf", navController = rememberNavController())
}