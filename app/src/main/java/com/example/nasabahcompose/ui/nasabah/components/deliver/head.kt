package com.example.nasabahcompose.ui.nasabah.components.deliver

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.nasabahcompose.R

@Composable
fun DeliveryHeaderSection(navController: NavHostController? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {

        // Konten tengah
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.antar_ic), // ganti dengan ilustrasi kamu
                contentDescription = "Antar Sendiri",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 12.dp)
            )

            Column {
                Text(
                    text = "Antar Sendiri",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A2B6E)
                )
                Text(
                    text = "Segera dijemput dari lokasimu",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHead() {
    DeliveryHeaderSection()
}
