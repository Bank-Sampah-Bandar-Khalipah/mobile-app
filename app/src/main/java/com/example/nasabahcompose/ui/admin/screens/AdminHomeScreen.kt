package com.example.nasabahcompose.ui.admin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nasabahcompose.data.dummy.dummySetoranList
import com.example.nasabahcompose.model.Setoran

data class GridItem(
    val title: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeScreen(
    username: String,
    navController: NavController
) {
    val scrollState = rememberScrollState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("admin_add") },
                containerColor = Color(0xFF0B1D51),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .verticalScroll(scrollState)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hi $username,",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Selamat Datang!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

                IconButton(onClick = {/* TODO */ }) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications"
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            AdminFeatureGrid(navController = navController)

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Setoran Petugas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0B1D51)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ReportTable(reports = dummySetoranList)

            Spacer(modifier = Modifier.height(24.dp))
        }

    }
}

@Composable
fun AdminFeatureGrid(navController: NavController) {
    val features = listOf(
        GridItem("Permintaan Pencairan Dana", Icons.Default.AccountBalanceWallet),
        GridItem("Pengangkutan Sampah", Icons.Default.Delete),
        GridItem("Informasi Pengumpulan", Icons.Default.Description),
        GridItem("Edit Informasi Sampah", Icons.Default.Edit)
    )

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            GridItemCard(
                item = features[0],
                onClick = { navController.navigate("admin_withdrawal") },
                modifier = Modifier.weight(1f)
            )
            GridItemCard(
                item = features[1],
                onClick = { navController.navigate("admin_pickup") },
                modifier = Modifier.weight(1f)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            GridItemCard(
                item = features[2],
                onClick = { navController.navigate("admin_gathering") },
                modifier = Modifier.weight(1f)
            )
            GridItemCard(
                item = features[3],
                onClick = { navController.navigate("admin_edit_information") },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItemCard(
    item: GridItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .aspectRatio(1f),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF0B1D51)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ReportTable(reports: List<Setoran>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("No", modifier = Modifier.weight(0.5f), style = tableHeaderStyle())
                Text("ID Laporan", modifier = Modifier.weight(1.5f), style = tableHeaderStyle())
                Text("Nama Petugas", modifier = Modifier.weight(1.5f), style = tableHeaderStyle())
                Text("Tanggal", modifier = Modifier.weight(1.0f), style = tableHeaderStyle())
            }
            HorizontalDivider(color = Color(0xFFF0F0F0))

            reports.forEachIndexed { index, report ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        String.format("%02d", index + 1),
                        modifier = Modifier.weight(0.5f),
                        style = tableDataStyle()
                    )
                    Text(report.id, modifier = Modifier.weight(1.5f), style = tableDataStyle())
                    Text(
                        report.namaPetugas,
                        modifier = Modifier.weight(1.5f),
                        style = tableDataStyle()
                    )
                    Text(report.tanggal, modifier = Modifier.weight(1.0f), style = tableDataStyle())
                }
                if (index < reports.lastIndex) {
                    HorizontalDivider(color = Color(0xFFF0F0F0))
                }
            }
        }
    }
}

@Composable
private fun tableHeaderStyle() = androidx.compose.ui.text.TextStyle(
    fontWeight = FontWeight.SemiBold,
    color = Color.Gray,
    fontSize = 14.sp
)

@Composable
private fun tableDataStyle() = androidx.compose.ui.text.TextStyle(
    fontWeight = FontWeight.Normal,
    color = Color.Black,
    fontSize = 14.sp
)