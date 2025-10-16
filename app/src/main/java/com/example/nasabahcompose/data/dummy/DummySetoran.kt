package com.example.nasabahcompose.data.dummy

import com.example.nasabahcompose.model.SampahItem
import com.example.nasabahcompose.model.Setoran

fun hitungAmount(jenis: String, beratKg: Double): String {
    val hargaPerKg = when (jenis.lowercase()) {
        "botol plastik", "plastik" -> 500
        "kertas" -> 1000
        "kardus" -> 1500
        else -> 0
    }
    val total = hargaPerKg * beratKg
    return "Rp ${"%,.0f".format(total)}"
}

fun hitungPoin(jenis: String, beratKg: Double): Int {
    val hargaPerKg = when (jenis.lowercase()) {
        "botol plastik", "plastik" -> 500
        "kertas" -> 1000
        "kardus" -> 1500
        else -> 0
    }
    return (hargaPerKg * beratKg).toInt()
}

val dummySetoranList = listOf(
    Setoran(
        id = "S001",
        status = "Selesai",
        namaPetugas = "Budi Santoso",
        tanggal = "14 Agustus 2025",
        sampah = listOf(
            SampahItem("Botol Plastik", 3.5, hitungAmount("Botol Plastik", 3.5), hitungPoin("Botol Plastik", 3.5)),
            SampahItem("Kertas", 2.0, hitungAmount("Kertas", 2.0), hitungPoin("Kertas", 2.0)),
            SampahItem("Kardus", 5.0, hitungAmount("Kardus", 5.0), hitungPoin("Kardus", 5.0))
        )
    ),
    Setoran(
        id = "S002",
        status = "Proses",
        namaPetugas = "Siti Aminah",
        tanggal = "12 Agustus 2025",
        sampah = listOf(
            SampahItem("Botol Plastik", 1.2, hitungAmount("Botol Plastik", 1.2), hitungPoin("Botol Plastik", 1.2)),
            SampahItem("Kertas", 1.0, hitungAmount("Kertas", 1.0), hitungPoin("Kertas", 1.0))
        )
    ),
    Setoran(
        id = "S003",
        status = "Selesai",
        namaPetugas = "Agus Saputra",
        tanggal = "10 Agustus 2025",
        sampah = listOf(
            SampahItem("Kardus", 4.3, hitungAmount("Kardus", 4.3), hitungPoin("Kardus", 4.3))
        )
    )
)
