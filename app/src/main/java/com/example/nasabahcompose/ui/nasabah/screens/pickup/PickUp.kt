package com.example.nasabahcompose.ui.nasabah.screens.pickup

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.PickupDetail
import com.example.nasabahcompose.ui.nasabah.components.commons.CommonHeader
import com.example.nasabahcompose.ui.nasabah.components.commons.WarningAlertDialog
import com.example.nasabahcompose.ui.nasabah.components.deliver.DatePickerField
import com.example.nasabahcompose.ui.nasabah.components.deliver.JenisSampahCard
import com.example.nasabahcompose.ui.nasabah.components.deliver.LocationCardWithInput
import com.example.nasabahcompose.ui.nasabah.components.deliver.TimePickerField
import com.google.gson.Gson
import java.util.*

@Composable
fun PickUpScreen(navController: NavHostController) {
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var currentAddress by remember { mutableStateOf("") }

    var plastikWeight by remember { mutableStateOf(0f) }
    var plastikPrice by remember { mutableStateOf(0) }

    var kardusWeight by remember { mutableStateOf(0f) }
    var kardusPrice by remember { mutableStateOf(0) }

    var kertasWeight by remember { mutableStateOf(0f) }
    var kertasPrice by remember { mutableStateOf(0) }

    var showWarningDialog by remember { mutableStateOf(false) }
    var warningMessage by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    WarningAlertDialog(
        showDialog = showWarningDialog,
        onDismiss = { showWarningDialog = false },
        message = warningMessage
    )

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFF4F2F2))
                .fillMaxSize()
        ) {
            CommonHeader(
                title = "Informasi Tempat",
                navController = navController
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                LocationCardWithInput(
                    currentAddress = currentAddress,
                    onAddressChange = { newAddress ->
                        val wordCount = newAddress.trim().split("\\s+".toRegex()).size
                        if (wordCount <= 10) {
                            currentAddress = newAddress
                        }
                    }
                )

                Text(
                    text = "${currentAddress.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }.size}/10 kata",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Detail Penjemputan",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                DatePickerField(
                    selectedDate = selectedDate,
                    onDateSelected = { selectedDate = it }
                )

                Spacer(modifier = Modifier.height(12.dp))

                TimePickerField(
                    selectedTime = selectedTime,
                    onTimeSelected = { selectedTime = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Jenis Sampah",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                JenisSampahCard(
                    name = "Plastik",
                    iconRes = R.drawable.botol,
                    pricePerKg = 1000,
                    onWeightChanged = { weight, price ->
                        plastikWeight = weight
                        plastikPrice = price
                    }
                )

                JenisSampahCard(
                    name = "Kardus",
                    iconRes = R.drawable.kardus2,
                    pricePerKg = 1000,
                    onWeightChanged = { weight, price ->
                        kardusWeight = weight
                        kardusPrice = price
                    }
                )

                JenisSampahCard(
                    name = "Kertas",
                    iconRes = R.drawable.kertas,
                    pricePerKg = 1000,
                    onWeightChanged = { weight, price ->
                        kertasWeight = weight
                        kertasPrice = price
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        val totalWeight = plastikWeight + kardusWeight + kertasWeight

                        when {
                            currentAddress.trim().isEmpty() -> {
                                warningMessage = "Mohon masukkan alamat Anda."
                                showWarningDialog = true
                            }
                            totalWeight == 0f -> {
                                warningMessage = "Mohon pilih minimal satu jenis sampah dan masukkan beratnya."
                                showWarningDialog = true
                            }
                            totalWeight < 1f -> {
                                warningMessage = "Total berat sampah minimal 1 kg. Total saat ini: ${"%.1f".format(totalWeight)} kg"
                                showWarningDialog = true
                            }
                            selectedDate.isEmpty() -> {
                                warningMessage = "Mohon pilih tanggal penjemputan."
                                showWarningDialog = true
                            }
                            selectedTime.isEmpty() -> {
                                warningMessage = "Mohon pilih waktu penjemputan."
                                showWarningDialog = true
                            }
                            else -> {
                                val listSampah = mutableListOf<Triple<String, Double, Int>>()

                                if (plastikWeight > 0) {
                                    listSampah.add(Triple("Plastik", plastikWeight.toDouble(), plastikPrice))
                                }
                                if (kardusWeight > 0) {
                                    listSampah.add(Triple("Kardus", kardusWeight.toDouble(), kardusPrice))
                                }
                                if (kertasWeight > 0) {
                                    listSampah.add(Triple("Kertas", kertasWeight.toDouble(), kertasPrice))
                                }

                                val idLaporan = UUID.randomUUID().toString().substring(0, 8).uppercase()
                                val petugas = "Lionel Messi"
                                val tanggalWaktu = "$selectedDate $selectedTime"

                                val pickupDetail = PickupDetail(
                                    idLaporan = idLaporan,
                                    petugas = petugas,
                                    tanggalWaktu = tanggalWaktu,
                                    listSampah = listSampah
                                )

                                val addressEncoded = Uri.encode(currentAddress.trim())
                                val dataJson = Uri.encode(Gson().toJson(pickupDetail))

                                navController.navigate("penjemputan/$dataJson/$addressEncoded")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0A2B6E)),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("Selanjutnya", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}