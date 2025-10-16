package com.example.nasabahcompose.ui.nasabah.components.commons

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nasabahcompose.R

@Composable
fun MyNavbarBar(navController: NavController, username: String) {
    val items = listOf(
        BottomMenuItem("Home", painterResource(R.drawable.ic_home), "home"),
        BottomMenuItem("History", painterResource(R.drawable.ic_history), "history"),
        BottomMenuItem("Ask", painterResource(R.drawable.ic_ask), "ask"),
        BottomMenuItem("User", painterResource(R.drawable.ic_user), "user")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    NavigationBar (
        containerColor = Color.White
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.label,
                        tint = if (isSelected) Color(0xFF1A4AC3) else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomMenuItem(val label: String, val icon: Painter, val route: String)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem("Home", painterResource(R.drawable.ic_home), "home"),
        BottomMenuItem("History", painterResource(R.drawable.ic_history), "history"),
        BottomMenuItem("Ask", painterResource(R.drawable.ic_ask), "ask"),
        BottomMenuItem("User", painterResource(R.drawable.ic_user), "user")
    )
}

