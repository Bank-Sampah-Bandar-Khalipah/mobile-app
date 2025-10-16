package com.example.nasabahcompose.data

data class PickupDetail(
    val idLaporan: String,
    val petugas: String,
    val tanggalWaktu: String,
    val listSampah: List<Triple<String, Double, Int>>
)

