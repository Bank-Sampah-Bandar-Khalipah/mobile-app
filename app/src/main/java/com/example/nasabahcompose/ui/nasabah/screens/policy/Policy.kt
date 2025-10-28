package com.example.nasabahcompose.ui.nasabah.screens.policy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Kebijakan Privasi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Terakhir diperbaharui: 30 Juni 2025",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF888888),
                    style = MaterialTheme.typography.bodyMedium.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Selamat datang di Aplikasi Bank Sampah Bandar Khalipah.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Kebijakan Privasi ini berlaku untuk Platform kami dan situs web, layanan, aplikasi, dan konten terkait kami yang tersedia di Platform kami yang disediakan oleh kami.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Kami berkomitmen untuk melindungi dan menghormati privasi pengguna (\"Anda\" atau \"milik Anda\"). Kebijakan Privasi ini menjelaskan bagaimana kami mengumpulkan, menggunakan, membagikan, dan memproses informasi pribadi Anda dan invidu lain sehubungan dengan Laynan kami. Jika Anda tidak setuju dengan Kebijakan Privasi ini, Anda tidak boleh mengakses atau menggunakan Layanan kami.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("1. INFORMASI APA YANG KAMI KUMPULKAN?")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kami dapat mengumpulkan informasi berikut tentang Anda:",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                BulletText("Informasi profil Anda.", "Anda memberi kami informasi saat mendaftar akun Anda di Platform, termasuk nama pengguna, kata sandi,dan informasi lain apa pun yang Anda ungkapkan di profil akun Anda.")

                BulletText("Informasi Transaksi Anda.", "Saat Anda melakukan transaksi berupa pengumpulan sampah, penyetoran sampah, permintaan pencairan dana, kami mengumpulkan informasi tentang transaksi Anda.")

                BulletText("Penelitian dan Pengembangan Layanan.", "Kami mengumpulkan informasi Anda melalui survei, penelitian, promosi, pemasaran, atau acara yang dilakukan oleh kami, di mana Anda berpartisipasi untuk mengembangkan, menguji, dan meningkatkan Layanan kami.")

                BulletText("Korespondensi Anda.", "Kami mengumpulkan korespondensi Anda dengan kami, termasuk saat Anda menghubungi kami untuk dukungan atau umpan balik.")

                BulletText("Informasi Teknis.", "Kami mengumpulkan informasi tertentu perangkat yang Anda gunakan untuk mengakses Layanan, seperti alamat IP Anda, operator seluler, model perangkat Anda, sistem perangkat, ID perangkat, resolusi layar Anda, sistem operasi, pengaturan audio, dan perangkat audio yang terhubung. Jika Anda masuk dari beberapa perangkat, kami akan dapat menggunakan informasi profil Anda untuk mengidentifikasi aktivitas Anda di berbagai perangkat. Kami juga mengaitkan Anda dengan informasi yang dikumpulkan dari perangkat selain perangkat yang Anda gunakan untuk masuk ke Platform.")

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("2. BAGAIMANA KAMI MEMBAGIKAN INFORMASI ANDA?")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kami membagikan informasi Anda dengan pihak-pihak berikut:",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Penyedia Layanan",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Kami memberikan informasi kepada penyedia layanan yang mendukung Layanan kami, seperti:",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                SubBulletText("Penyedia Layanan Penjemputan.", "Jika Anda memilih untuk penyetoran sampah menggunakan metode penjemputan di Platform, kami dapat membagikan informasi Anda dengan penyedia layanan penjemputan untuk memfasilitasi penjemputan penyetoran sampah Anda.")

                SubBulletText("Penyedia Layanan Cloud.", "Kami dapat membagikan informasi Anda dengan penyedia layanan cloud kami untuk memastikan bahwa Layanan beroperasi secara optimal.")

                SubBulletText("Peneliti Independen.", "Kami dapat membagikan informasi Anda dengan peneliti independen untuk memfasilitasi penelitian yang memenuhi kriteria tertentu.")

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Badan Penegak Hukum atau Regulator",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Kami akan membagikan informasi Anda dengan badan penegak hukum, regulator, atau organisasi lain jika kami diharuskan secara hukum untuk melakukannya, atau jika penggunaan tersebut wajar diperlukan untuk:",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                SimpleBulletText("mematuhi kewajiban, proses, atau permintaan hukum apa pun;")
                SimpleBulletText("menegakkan Syarat dan Ketentuan dan perjanjian, kebijakan, dan standar lainnya, termasuk penyelidikan atas potensi pelanggaran;")
                SimpleBulletText("mendeteksi, mencegah, atau mengatasi masalah keamanan, penipuan, atau teknis;")
                SimpleBulletText("melindungi hak kami, pengguna kami, pihak ketiga, atau publik sebagaimana dipersyaratkan atau diizinkan oleh hukum")

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("3. DIMANA KAMI MENYIMPAN INFORMASI ANDA?")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kami bekerja sama dengan penyedia layanan cloud kami untuk, antara lain, menyimpan informasi Anda. Informasi Anda dapat disimpan di server.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("4. BAGAIMANA ANDA MENGGUNAKAN HAK ANDA?")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Anda memiliki hak-hak tertentu berdasarkan hukum yang berlaku, yang mungkin termasuk hak untuk mengakses, menghapus, memperbarui, atau memperbaiki data Anda, untuk dibertahu tentang pemrosesan data Anda, untuk mengajukan keluhan kepada otoritas, dan potensi hak lainnya.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Anda dapat mengirimkan permintaan untuk menggunakan hak Anda berdasarkan hukum yang berlaku melalui bagian Hubungi Kami di bawah ini. Anda dapat mengakses dan mengedit sebagian besar informasi profil akun Anda dengan masuk ke Platform.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("5. KEAMANAN INFORMASI ANDA")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kami akan menggunakan tindakan wajar untuk melindungi informasi Anda. Namun, kami tidak dapat menjamin keamanan informasi Anda yang dikirimkan melalui Layanan. Setiap transmisi dilakukan atas risiko Anda sendiri. Kami memelihara langkah-langkah teknis dan organisasi dan akan memperbaikinya dari waktu ke waktu untuk meningkatkan keamanan keseluruhan Layanan kami.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kami dapat menyertakan tautan ke dan dari situs web atau platform ini. Harap luangkan waktu untuk membaca kebijakan ini dengan cermat sebelum And mengirimkan informasi apa pun ke situs web atau platform ini.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("6. BERAPA LAMA KAMI MENYIMPAN INFORMASI ANDA?")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kami menyimpan informasi selama yang diperlukan untuk menyediakan Platform dan untuk tujuan lain yang tercantum dalam Kebijakan Privasi ini. Kami juga menyimpan informasi jika diperlukan untuk memenuhi kewajiban kontraktual dan hukum, ketika kami memiliki kepentingan bisnis yang sah untuk melakukannya (seperti meningkatkan dan mengembangkan Platform, dan meningkatkan keamanan dan stabilitasnya), dan untuk pelaksanaan atau pembelaan klaim hukum.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Periode penyimpanan berbeda tergantung pada kriteria yang berbeda, seperti jenis informasi dan tujuan penggunaan informasi oleh kami. Misalnya, ketika kami memproses informasi Anda seperti informasi profil Anda untuk memberi Anda Layanan, kami menyimpan informasi ini selama Anda memiliki akun di Platform. Jika Anda melanggar Persyaratan dan Ketentuan kami, atau ketentuan atau kebijakan lainnya, kami dapat menghentikan akun Anda atau membatasi akses ke Layanan kami, tetapi kami dapat menyimpan informasi lain tentang Anda untuk memproses pelanggaran tersebut.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("7. PRIVASI ANAK")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kami tidak secara sengaja mengumpulkan informasi dari siapa pun yang berusia di bawah 18 tahun. Jika kami menemukan bahwa individu di bawah usia 18 tahun telah memberi kami informasi pribadi, kami akan segera menghapus informasi ini. Jika Anda adalah orang tua atau wali dan Anda mengetahui bahwa anak Anda telah memberi kami informasi pribadi tanpa izin Anda, silakan hubungi kami melalui bagian Hubungi Kami di bawah ini.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("8. BAGAIMANA KAMI AKAN MEMBERI TAHU ANDA TENTANG PERUBAHAN PADA KEBIJAKAN PRIVASI INI?")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kami dapat memperbarui Kebijakan Privasi ini dari waktu ke waktu. Kecuali jika diharuskan oleh hukum, kami akan memberi tahu Anda (misalnya, melalui Layanan kami) sebelum kami melakukan perubahan pada Kebijakan Privasi ini dan memberi Anda kesempatan untuk meninjau Kebijakan Privasi yang diperbarui sebelum Kebijakan Privasi yang diperbarui berlaku. Akses berkelanjutan Anda ke atau penggunaan Layanan setelah tanggal Kebijakan Privasi yang diperbarui merupakan penerimaan Anda terhadap Kebijakan Privasi yang diperbarui. Jika Anda tidak setuju dengan Kebijakan Privasi yang diperbarui, Anda harus berhenti mengakses atau menggunakan Layanan.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("9. BAHASA")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kebijakan Privasi ini hanya tersedia dalam bahasa Indonesia.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("10. HUBUNGI KAMI")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Jika Anda memiliki pertanyaan, komentar, keluhan, atau permintaan terkait Kebijakan Privasi ini, silakan hubungi kami di: privacy@bank.sampah.",
                    fontSize = 15.sp,
                    color = Color(0xFF333333),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF333333)
    )
}

@Composable
fun BulletText(title: String, description: String) {
    Row(
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        Text(
            text = "• ",
            fontSize = 15.sp,
            color = Color(0xFF333333),
            modifier = Modifier.padding(end = 4.dp)
        )
        Column {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333333)
            )
            Text(
                text = description,
                fontSize = 15.sp,
                color = Color(0xFF333333),
                lineHeight = 22.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun SubBulletText(title: String, description: String) {
    Row(
        modifier = Modifier.padding(bottom = 12.dp, start = 8.dp)
    ) {
        Text(
            text = "• ",
            fontSize = 15.sp,
            color = Color(0xFF333333),
            modifier = Modifier.padding(end = 4.dp)
        )
        Column {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333333)
            )
            Text(
                text = description,
                fontSize = 15.sp,
                color = Color(0xFF333333),
                lineHeight = 22.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun SimpleBulletText(text: String) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
    ) {
        Text(
            text = "• ",
            fontSize = 15.sp,
            color = Color(0xFF333333),
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = text,
            fontSize = 15.sp,
            color = Color(0xFF333333),
            lineHeight = 22.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyPreview() {
    val navController = rememberNavController()
    PrivacyPolicyScreen(navController = navController, username = "")
}