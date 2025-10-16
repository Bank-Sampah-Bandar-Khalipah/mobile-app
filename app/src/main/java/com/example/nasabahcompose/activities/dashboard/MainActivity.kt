package com.example.nasabahcompose.activities.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.nasabahcompose.data.Trash
import com.example.nasabahcompose.ui.screens.*
import com.example.nasabahcompose.ui.nasabah.screens.RincianLaporanScreen // pastikan path benar
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.PickupDetail
import com.example.nasabahcompose.ui.auth.LoginScreen
import com.example.nasabahcompose.ui.nasabah.screens.DeliveryScreen
import com.example.nasabahcompose.ui.nasabah.screens.HelpScreen
import com.example.nasabahcompose.ui.nasabah.screens.HistoryScreen
import com.example.nasabahcompose.ui.nasabah.screens.HomeScreen
import com.example.nasabahcompose.ui.nasabah.screens.LocationScreen
import com.example.nasabahcompose.ui.nasabah.screens.PencairanDanaScreen
import com.example.nasabahcompose.ui.nasabah.screens.PenjemputanScreen
import com.example.nasabahcompose.ui.nasabah.screens.PickUpScreen
import com.example.nasabahcompose.ui.nasabah.screens.PrivacyPolicyScreen
import com.example.nasabahcompose.ui.nasabah.screens.SetoranListScreen
import com.example.nasabahcompose.ui.nasabah.screens.TrashPriceScreen
import com.example.nasabahcompose.ui.nasabah.screens.UserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dummyTrashList = listOf(
            Trash("Botol Plastik", 2000, R.drawable.sampah, "Plastik"),
            Trash("Kertas", 1000, R.drawable.buku, "Kertas"),
            Trash("Kardus", 1500, R.drawable.kardus2, "Kardus")
        )

        setContent {
            val navController = rememberNavController()
            var username by rememberSaveable { mutableStateOf("") }

            NavHost(navController = navController, startDestination = "login") {
                // Login
                composable("login") {
                    LoginScreen(onLoginSuccess = { fetchedUsername ->
                        username = fetchedUsername
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    })
                }

                // Bottom navbar / main screens (home, history, ask, user)
                composable("home") {
                    HomeScreen(username = username, navController = navController)
                }

                composable("history") {
                    HistoryScreen(username = username, navController = navController)
                }

                composable("ask") {
                    HelpScreen(username = username, navController = navController)
                }

                composable("user") {
                    UserScreen(username = username, navController = navController)
                }

                // Home features
                composable("trash_price") {
                    TrashPriceScreen(
                        navController = navController,
                        trashList = dummyTrashList
                    )
                }

                // Delivery / Pickup
                composable("delivery") {
                    DeliveryScreen(navController = navController)
                }

                composable("pickup") {
                    PickUpScreen(navController = navController)
                }

                // Slip setoran list
                composable("slip_setoran") {
                    SetoranListScreen(navController = navController)
                }

                composable("pencairan") {
                    PencairanDanaScreen(navController = navController)
                }

                // Location & Policy
                composable("location") {
                    LocationScreen(username = username, navController = navController)
                }

                composable("policy") {
                    PrivacyPolicyScreen(username = username, navController = navController)
                }

                // 🔹 Detail Setoran (menggunakan id dari route dan memanggil RincianLaporanScreen yang sudah mengambil data dari dummySetoranList)
                composable(
                    route = "detailSetoran/{setoranId}",
                    arguments = listOf(navArgument("setoranId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val setoranId = backStackEntry.arguments?.getString("setoranId") ?: ""
                    // Panggil screen yang sudah kita buat, yang akan mencari setoran berdasarkan id pada dummySetoranList
                    RincianLaporanScreen(
                        navController = navController,
                        idLaporan = setoranId
                    )
                }

                composable("delivery") {
                    DeliveryScreen(navController = navController)
                }

                composable(
                    route = "penjemputan/{dataJson}",
                    arguments = listOf(navArgument("dataJson") { type = NavType.StringType })
                ) { backStackEntry ->
                    val encoded = backStackEntry.arguments?.getString("dataJson") ?: ""
                    val decoded = java.net.URLDecoder.decode(encoded, java.nio.charset.StandardCharsets.UTF_8.toString())
                    val pickupDetail = com.google.gson.Gson().fromJson(decoded, PickupDetail::class.java)

                    PenjemputanScreen(
                        navController = navController,
                        pickupDetail = pickupDetail
                    )
                }

            }
        }
    }
}
