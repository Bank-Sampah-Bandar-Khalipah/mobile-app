package com.example.nasabahcompose.ui.nasabah.screens.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Refresh

@Composable
fun StatusBadge(status: String) {
    val (bgColor, textColor, icon) = when (status) {
        "Berhasil" -> Triple(Color(0xFFD1FADF), Color(0xFF027A48), Icons.Default.Check)
        "Proses" -> Triple(Color(0xFFFFF5D1), Color(0xFFB54708), Icons.Default.Refresh)
        "Gagal"    -> Triple(Color(0xFFFEE4E2), Color(0xFFB42318), Icons.Default.Close)
        else       -> Triple(Color.Gray, Color.White, Icons.Default.Info)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Icon(icon, contentDescription = null, tint = textColor, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(status, color = textColor, fontSize = 12.sp)
    }
}

@Preview
@Composable
fun PreviewStatusBadge() {
    Column {
        StatusBadge("Berhasil")
        Spacer(Modifier.height(4.dp))
        StatusBadge("Proses")
        Spacer(Modifier.height(4.dp))
        StatusBadge("Gagal")
        Spacer(Modifier.height(4.dp))
        StatusBadge("Tidak Diketahui")
    }
}
