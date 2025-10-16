package com.example.nasabahcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nasabahcompose.data.PickupDetail
import com.example.nasabahcompose.ui.nasabah.screens.pickup.DeliveryScreen
import com.example.nasabahcompose.ui.nasabah.screens.pickup.PenjemputanScreen
import com.google.gson.Gson
import java.net.URLDecoder
import androidx.navigation.NavHostController

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "delivery") {

        composable("delivery") {
            DeliveryScreen(navController = navController)
        }

        composable(
            route = "penjemputan/{dataJson}",
            arguments = listOf(navArgument("dataJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val jsonEncoded = backStackEntry.arguments?.getString("dataJson").orEmpty()
            val json = URLDecoder.decode(jsonEncoded, "UTF-8")
            val pickupDetail = Gson().fromJson(json, PickupDetail::class.java)

            PenjemputanScreen(
                navController = navController,
                pickupDetail = pickupDetail
            )
        }
    }
}
