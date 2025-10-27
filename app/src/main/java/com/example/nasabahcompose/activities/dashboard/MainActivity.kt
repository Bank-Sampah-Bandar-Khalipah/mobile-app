package com.example.nasabahcompose.activities.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.PickupDetail
import com.example.nasabahcompose.data.Trash
import com.example.nasabahcompose.ui.admin.components.AdminBottomNavigation
import com.example.nasabahcompose.ui.admin.screens.AdminAddScreen
import com.example.nasabahcompose.ui.admin.screens.AdminEditInformationScreen
import com.example.nasabahcompose.ui.admin.screens.AdminEditPasswordScreen
import com.example.nasabahcompose.ui.admin.screens.AdminEditProfileScreen
import com.example.nasabahcompose.ui.admin.screens.AdminGatheringScreen
import com.example.nasabahcompose.ui.admin.screens.AdminHomeScreen
import com.example.nasabahcompose.ui.admin.screens.AdminPickUpScreen
import com.example.nasabahcompose.ui.admin.screens.AdminPrivacyPolicyScreen
import com.example.nasabahcompose.ui.admin.screens.AdminUserScreen
import com.example.nasabahcompose.ui.admin.screens.AdminWithdrawalScreen
import com.example.nasabahcompose.ui.auth.LoginScreen
import com.example.nasabahcompose.ui.nasabah.screens.HelpScreen
import com.example.nasabahcompose.ui.nasabah.screens.HistoryScreen
import com.example.nasabahcompose.ui.nasabah.screens.HomeScreen
import com.example.nasabahcompose.ui.nasabah.screens.UserScreen
import com.example.nasabahcompose.ui.nasabah.screens.deposit.RincianLaporanScreen
import com.example.nasabahcompose.ui.nasabah.screens.deposit.SetoranListScreen
import com.example.nasabahcompose.ui.nasabah.screens.loc.LocationScreen
import com.example.nasabahcompose.ui.nasabah.screens.pickup.DeliveryScreen
import com.example.nasabahcompose.ui.nasabah.screens.pickup.PenjemputanScreen
import com.example.nasabahcompose.ui.nasabah.screens.pickup.PengantaranScreen
import com.example.nasabahcompose.ui.nasabah.screens.pickup.PickUpScreen
import com.example.nasabahcompose.ui.nasabah.screens.point.PencairanDanaScreen
import com.example.nasabahcompose.ui.nasabah.screens.policy.PrivacyPolicyScreen
import com.example.nasabahcompose.ui.nasabah.screens.trash.TrashPriceScreen
import com.example.nasabahcompose.utils.AuthManager

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
            var userRole by rememberSaveable { mutableStateOf("") }
            var isCheckingAuth by remember { mutableStateOf(true) }
            var startDestination by remember { mutableStateOf("login") }

            // Check if user is already logged in
            LaunchedEffect(Unit) {
                AuthManager.checkUserAuth(
                    context = this@MainActivity,
                    onResult = { isLoggedIn, savedUsername, savedRole ->
                        if (isLoggedIn) {
                            username = savedUsername
                            userRole = savedRole
                            startDestination = if (savedRole == "admin") "admin_home" else "home"
                        } else {
                            startDestination = "login"
                        }
                        isCheckingAuth = false
                    }
                )
            }

            if (isCheckingAuth) {
                // Show loading while checking auth
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF0B1D51))
                }
            } else {
                NavHost(navController = navController, startDestination = startDestination) {
                    // Login Screen
                    composable("login") {
                        LoginScreen(onLoginSuccess = { userData ->
                            username = userData.username
                            userRole = userData.role

                            // Save login state
                            AuthManager.saveLoginState(
                                this@MainActivity,
                                userData.username,
                                userRole
                            )

                            // Navigate based on role
                            if (userRole == "admin") {
                                navController.navigate("admin_home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } else {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        })
                    }

                    // ============ ADMIN ROUTES ============
                    composable("admin_home") {
                        val currentRoute = navController.currentBackStackEntry?.destination?.route

                        Scaffold(
                            bottomBar = {
                                AdminBottomNavigation(
                                    navController = navController,
                                    currentRoute = currentRoute
                                )
                            }
                        ) { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminHomeScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }

                    composable("admin_withdrawal") {
                        Scaffold { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminWithdrawalScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }
                    composable("admin_edit_information") {
                        Scaffold { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminEditInformationScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }
                    composable("admin_pickup") {
                        Scaffold { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminPickUpScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }
                    composable("admin_gathering") {
                        Scaffold { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminGatheringScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }

                    composable("admin_add") {
                        Scaffold { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminAddScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }

                    composable("admin_user") {
                        val currentRoute = navController.currentBackStackEntry?.destination?.route

                        Scaffold(
                            bottomBar = {
                                AdminBottomNavigation(
                                    navController = navController,
                                    currentRoute = currentRoute
                                )
                            }
                        ) { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminUserScreen(
                                    username = username,
                                    navController = navController,
                                    onLogout = {
                                        // Clear login state and navigate to login
                                        AuthManager.logout(this@MainActivity)
                                        navController.navigate("login") {
                                            popUpTo(0) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        }
                    }

                    composable("admin_edit_profile") {
                        Scaffold { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminEditProfileScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }

                    composable("admin_edit_password") {
                        Scaffold { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminEditPasswordScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }

                    composable("admin_privacy_policy") {
                        Scaffold { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                AdminPrivacyPolicyScreen(
                                    username = username,
                                    navController = navController
                                )
                            }
                        }
                    }

                    // ============ NASABAH ROUTES ============
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
                        UserScreen(
                            username = username,
                            navController = navController
                        )
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

                    // Detail Setoran
                    composable(
                        route = "detailSetoran/{setoranId}",
                        arguments = listOf(navArgument("setoranId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val setoranId = backStackEntry.arguments?.getString("setoranId") ?: ""
                        RincianLaporanScreen(
                            navController = navController,
                            idLaporan = setoranId
                        )
                    }

                    composable(
                        route = "penjemputan/{dataJson}/{userAddress}",
                        arguments = listOf(
                            navArgument("dataJson") { type = NavType.StringType },
                            navArgument("userAddress") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val encoded = backStackEntry.arguments?.getString("dataJson") ?: ""
                        val addressEncoded =
                            backStackEntry.arguments?.getString("userAddress") ?: ""

                        val decoded = java.net.URLDecoder.decode(
                            encoded,
                            java.nio.charset.StandardCharsets.UTF_8.toString()
                        )
                        val userAddress = java.net.URLDecoder.decode(
                            addressEncoded,
                            java.nio.charset.StandardCharsets.UTF_8.toString()
                        )

                        val pickupDetail =
                            com.google.gson.Gson().fromJson(decoded, PickupDetail::class.java)

                        PenjemputanScreen(
                            navController = navController,
                            pickupDetail = pickupDetail,
                            userAddress = userAddress
                        )
                    }

                    composable(
                        route = "pengantaran/{dataJson}",
                        arguments = listOf(navArgument("dataJson") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val encoded = backStackEntry.arguments?.getString("dataJson") ?: ""
                        val decoded = java.net.URLDecoder.decode(
                            encoded,
                            java.nio.charset.StandardCharsets.UTF_8.toString()
                        )
                        val pickupDetail = com.google.gson.Gson().fromJson(
                            decoded,
                            PickupDetail::class.java
                        )

                        PengantaranScreen(
                            navController = navController,
                            pickupDetail = pickupDetail
                        )
                    }
                }
            }
        }
    }
}