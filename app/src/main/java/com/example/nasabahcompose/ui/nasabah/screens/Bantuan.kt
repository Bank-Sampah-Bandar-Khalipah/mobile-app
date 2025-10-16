package com.example.nasabahcompose.ui.nasabah.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nasabahcompose.R
import com.example.nasabahcompose.ui.nasabah.screens.components.CommonScreenHeader
import com.example.nasabahcompose.ui.nasabah.screens.components.MyNavbarBar

@Composable
fun HelpScreen(username: String, navController: NavController) {
    Scaffold(
        bottomBar = { MyNavbarBar(navController = navController, username = username) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F2F2))
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header konsisten
            CommonScreenHeader(
                title = "Bantuan",
                modifier = Modifier.fillMaxWidth()
            )

            Image(
                painter = painterResource(id = R.drawable.ask_for_help),
                contentDescription = "Ask for Help",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Perlu Bantuan?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Customer Service kami siap untuk membantu",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            HelpOptionItem(
                icon = R.drawable.ic_wa,
                title = "Whatsapp",
                subtitle = "Hubungi melalui whatsapp kami",
                url = "https://wa.me/6281234567890"
            )

            HelpOptionItem(
                icon = R.drawable.ic_ig,
                title = "Instagram",
                subtitle = "Hubungi melalui instagram kami",
                url = "https://instagram.com"
            )

            HelpOptionItem(
                icon = R.drawable.ic_fb,
                title = "Facebook",
                subtitle = "Hubungi melalui facebook kami",
                url = "https://facebook.com"
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun HelpOptionItem(icon: Int, title: String, subtitle: String, url: String) {
    val uriHandler = LocalUriHandler.current

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { uriHandler.openUri(url) },
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHelpScreen() {
    HelpScreen(username = "Yusuf", navController = rememberNavController())
}