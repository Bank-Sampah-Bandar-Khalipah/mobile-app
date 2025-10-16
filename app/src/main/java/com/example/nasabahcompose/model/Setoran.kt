package com.example.nasabahcompose.model

data class SampahItem(
    val jenis: String,
    val beratKg: Double,
    val amount: String,
    val poin: Int // ✅ tambahan untuk simpan poin langsung
)

data class Setoran(
    val id: String,
    val status: String,
    val namaPetugas: String,
    val tanggal: String,
    val sampah: List<SampahItem>
) {
    val totalBerat: Double
        get() = sampah.sumOf { it.beratKg }

    val totalPoin: Int
        get() = sampah.sumOf { it.poin }
}
