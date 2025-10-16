package com.example.nasabahcompose.ui.nasabah.screens.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeHeader(
    name: String,
    points: String,
    onNotificationClick: () -> Unit = {},
    onRedeemClick: () -> Unit = {}
) {
    Column {
        Box {
            // Greeting Section di belakang
            GreetingSection(name = name, onNotificationClick = onNotificationClick)

            // PointCard mengapung di bawah greeting (overlap)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = 120.dp) // atur posisi floating di bawah header
            ) {
                PointCard(points = points, onRedeemClick = onRedeemClick)
            }
        }

        Spacer(modifier = Modifier.height(60.dp)) // beri ruang di bawah kartu
    }
}

@Preview(showBackground = true)
@Composable
fun HomeHeaderPreview() {
    HomeHeader(name = "Alex", points = "30.000")
}
