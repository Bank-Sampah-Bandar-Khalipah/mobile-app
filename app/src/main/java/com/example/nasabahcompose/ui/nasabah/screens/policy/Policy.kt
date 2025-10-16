package com.example.nasabahcompose.ui.nasabah.screens.policy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nasabahcompose.ui.nasabah.components.commons.CommonHeader

@Composable
fun PrivacyPolicyScreen(navController: NavController, username: String) {
    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF4F2F2))
        ) {
            // Header menggunakan CommonHeader
            CommonHeader(
                title = "Kebijakan Privasi",
                navController = navController
            )

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(horizontal = 48.dp, vertical = 16.dp)
            ) {
                Text("Kebijakan Privasi", fontSize = 16.sp, color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Terakhir diperbarui: 30 Juni 2025", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("1. Informasi Apa yang Kami Kumpulkan?")
                BulletText("Informasi Profil: Nama pengguna, kata sandi, dsb.")
                BulletText("Informasi Transaksi: Penyetoran dan penarikan.")
                BulletText("Penelitian & Pengembangan: Dari survei, dsb.")
                BulletText("Korespondensi: Kontak dengan kami.")
                BulletText("Informasi Teknis: Alamat IP, perangkat, dsb.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("2. Bagaimana Kami Membagikan Informasi Anda?")
                BulletText("Penyedia layanan: penjemputan, cloud, peneliti.")
                BulletText("Lembaga hukum: bila diperlukan oleh hukum.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("3. Di Mana Kami Menyimpan Informasi Anda?")
                BulletText("Di server penyedia layanan cloud kami.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("4. Bagaimana Anda Menggunakan Hak Anda?")
                BulletText("Mengakses, memperbarui, memperbaiki, atau menghapus data.")
                BulletText("Keluhan ke otoritas.")
                BulletText("Edit langsung di aplikasi.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("5. Keamanan Informasi Anda")
                BulletText("Kami berusaha menjaga keamanan, tapi tidak bisa menjamin 100% aman.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("6. Berapa Lama Kami Menyimpan Informasi Anda?")
                BulletText("Selama akun aktif atau sesuai hukum.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("7. Privasi Anak")
                BulletText("Kami tidak mengumpulkan data anak di bawah 18 tahun.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("8. Pembaruan Kebijakan Privasi")
                BulletText("Kami akan memberi tahu Anda jika ada perubahan.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("9. Bahasa")
                BulletText("Kebijakan ini tersedia hanya dalam Bahasa Indonesia.")

                Spacer(modifier = Modifier.height(12.dp))

                SectionTitle("10. Hubungi Kami")
                BulletText("Email: privacy@bank.sampah")
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun BulletText(text: String) {
    Row(modifier = Modifier.padding(bottom = 4.dp)) {
        Text("• ", fontSize = 14.sp)
        Text(text, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyPreview() {
    val navController = rememberNavController()
    PrivacyPolicyScreen(navController = navController, username = "")
}