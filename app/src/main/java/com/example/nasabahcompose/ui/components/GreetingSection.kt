package com.example.nasabahcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nasabahcompose.ui.theme.NasabahComposeTheme   // ganti sesuai nama theme‑mu

@Composable
fun GreetingSection(
    name: String,
    onNotificationClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)                                             // tinggi header
            .clip(
                RoundedCornerShape(
                    bottomStart = 32.dp,
                    bottomEnd  = 32.dp
                )
            )
            .background(Color(0xFF0A2B6E))                              // warna biru header
    ) {
        // Teks sapaan
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 56.dp, end = 24.dp)
        ) {
            Text(
                text  = "Hi $name,",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text  = "Selamat Datang!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }

        // Ikon lonceng di kanan‑atas (opsional)
        IconButton(
            onClick = onNotificationClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 40.dp, end = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifikasi",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingSectionPreview() {
    NasabahComposeTheme {                      // gunakan Theme‑mu sendiri
        GreetingSection(name = "Alex")
    }
}
