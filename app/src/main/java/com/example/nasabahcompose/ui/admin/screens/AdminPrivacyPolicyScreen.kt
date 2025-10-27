package com.example.nasabahcompose.ui.admin.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPrivacyPolicyScreen(
    username: String,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Kebijakan Privasi",
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = lightGrayBg,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = lightGrayBg
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            item {
                Text(
                    text = "Kebijakan Privasi",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Terakhir diperbaharui 30 Juni 2025",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                PolicyParagraph(
                    text = "Selamat datang di Aplikasi Bank Sampah Bandar Khalipah. " +
                            "Kebijakan Privasi ini berlaku untuk Platform kami dan situs web, layanan, " +
                            "aplikasi, dan konten terkait kami yang tersedia di Platform kami yang " +
                            "disediakan oleh kami."
                )
                PolicyParagraph(
                    text = "Kami berkomitmen untuk melindungi dan menghormati privasi pengguna " +
                            "\"Anda\" atau \"milik Anda\"), Kebijakan Privasi ini menjelaskan bagaimana kami " +
                            "mengumpulkan, menggunakan, membagikan, dan memproses informasi " +
                            "pribadi Anda dan invidu lain sehubungan dengan Laynan kami."
                )
                PolicyParagraph(
                    text = "Jika Anda tidak setuju dengan Kebijakan Privasi ini, Anda tidak boleh mengakses " +
                            "atau menggunakan Layanan kami."
                )

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("1. INFORMASI APA YANG KAMI KUMPULKAN?")
                PolicyParagraph("Kami dapat mengumpulkan informasi berikut tentang Anda:")
                PolicyParagraph(
                    "Informasi profil Anda. Anda memberi kami informasi saat " +
                            "mendaftar akun Anda di Platform, termasuk nama pengguna, kata " +
                            "sandi, dan informasi lain apa pun yang Anda ungkapkan di profil akun " +
                            "Anda."
                )
                PolicyParagraph(
                    "Informasi Transaksi Anda. Saat Anda melakukan transaksi berupa " +
                            "pengumpulan sampah, penyetoran sampah, permintaan pencairan dana, " +
                            "kami mengumpulkan informasi tentang transaksi Anda"
                )
                PolicyParagraph(
                    "Penelitian dan Pengembangan Layanan. Kami mengumpulkan " +
                            "Informasi Anda melalui sürvei penelitian, promosi, pemasaran, atau " +
                            "acara yang dilakukan oleh kami, di mana Anda berpartisipasi untuk " +
                            "mengembangkan, menguji, dan meningkatkan Layanan kami."
                )
                PolicyParagraph(
                    "Korespondensi Anda. Kami mengumpulkan korespondensi Anda " +
                            "dengan kami, termasuk saat Anda menghubungi kami untuk dukungan " +
                            "atau umpan balik."
                )
                PolicyParagraph(
                    "Informasi Teknis. Kami mengumpulkan informasi tertentu " +
                            "perangkat yang Anda gunakan untuk mengakses Layanan, seperti alamat " +
                            "IP Anda, operator seluler, model perangkat Anda, sistem perangkat. " +
                            "ID perangkat, resolusi layar Anda, sistem operasi, pengaturan audio, " +
                            "dan perangkat audio yang terhubung."
                )
                PolicyParagraph(
                    "Jika Anda masuk dari beberapa perangkat, kami akan " +
                            "dapat menggunakan informasi profil Anda untuk mengidentifikasi " +
                            "aktivitas Anda di berbagai perangkat. Kami juga mengaitkan " +
                            "Anda dengan informasi yang dikumpulkan dari perangkat selain " +
                            "perangkat yang Anda gunakan untuk masuk ke Platform."
                )

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("2. BAGAIMANA KAMI MEMBAGIKAN INFORMASI ANDA?")
                PolicyParagraph("Kami membagikan informasi Anda dengan pihak-pihak berikut:")
                PolicySubHeading("Penyedia Layanan")
                PolicyParagraph(
                    "Kami memberikan informasi kepada penyedia layanan yang mendukung " +
                            "Layanan kami, seperti Penyedia Layanan Penjemputan. " +
                            "Jika Anda memilih untuk penyetoran sampah menggunakan metode " +
                            "penjemputan di Platform, kami dapat membagikan informasi Anda dengan " +
                            "penyedia layanan penjemputan untuk memfasilitasi penjemputan " +
                            "penyetoran sampah Anda."
                )
                PolicySubHeading("Penyedia Layanan Cloud.")
                PolicyParagraph(
                    "Kami dapat membagikan Informasi Anda dengan penyedia layanan cloud kami " +
                            "untuk memastikan bahwa Layanan beroperasi secara optimal."
                )
                PolicySubHeading("Peneliti Independen.")
                PolicyParagraph(
                    "Kami dapat membagikan informasi Anda dengan peneliti independen untuk " +
                            "memfasilitasi penelitian yang memenuhi kriteria tertentu."
                )
                PolicySubHeading("Badan Penegak Hukum atau Regulator")
                PolicyParagraph(
                    "Kami akan membagikan informasi Anda dengan badan penegak hukum, " +
                            "regulator, atau organisasi lain jika kami diharuskan secara hukum untuk " +
                            "melakukannya, atau jika penggunaan tersebut wajar diperlukan untuk:"
                )
                PolicyBullet("mematuni kewajiban, proses, atau permintaan hukum apa pun;")
                PolicyBullet(
                    "menegakkan Syarat dan Ketentuan dan perjanjian, kebijakan, dan " +
                            "standar lainnya, termasuk penyelidikan atas potensi " +
                            "pelanggaran"
                )
                PolicyBullet(
                    "mendeteksi, mencegah, atau mengatasi masalah keamanan, " +
                            "penipuan, atau teknis;"
                )
                PolicyBullet(
                    "melindungi hak kami, pengguna kami, pihak ketiga, atau publik " +
                            "sebagaimana dipersyaratkan atau diizinkan olen hukum"
                )

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("3. DIMANA KAMI MENYIMPAN INFORMASI ANDA?")
                PolicyParagraph(
                    "Kami bekerja sama dengan penyedia layanan cloud kami untuk, antara lain, " +
                            "menyimpan informasi Anda. Informasi Anda dapat disimpan di server."
                )

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("4. BAGAIMANA ANDA MENGGUNAKAN HAK ANDA?")
                PolicyParagraph(
                    "Anda memiliki hak-hak tertentu berdasarkan hukum yang berlaku, yang " +
                            "mungkin termasük hak untuk mengakses, menghapus, memperbarui, " +
                            "atau memperbaiki data Anda, untuk dibertahu tentang pemrosesan data " +
                            "Anda, untuk mengajukan keluhan kepada otoritas, dan potensi hak " +
                            "lainnya."
                )
                PolicyParagraph(
                    "Anda dapat mengirimkan permintaan untuk menggunakan hak Anda " +
                            "berdasarkan hukum yang berlaku melalui bagian Hubungi Kami di bawah " +
                            "ini. Anda dapat mengakses dan mengedit sebagian besar informasi " +
                            "profil akun Anda dengan masuk ke Platform."
                )

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("5. KEAMANAN INFORMASI ANDA")
                PolicyParagraph(
                    "Kami akan menggunakan tindakan wajar untuk melindungi informasi Anda. " +
                            "Namun, kami tidak dapat menjamin keamanan informasi Anda yang " +
                            "dikirimkan melalui Layanan. Setiap transmisi dilakukan atas risiko Anda " +
                            "sendiri. Kami memelihara langkah-langkah teknis dan organisasi dan akan " +
                            "memperbaikinya dari waktu ke waktu untuk meningkatkan keamanan " +
                            "keseluruhan Layanan kami."
                )
                PolicyParagraph(
                    "Kami dapat menyertakan tautan ke dan dari situs web atau platform ini. Harap " +
                            "luangkan waktu untuk membaca kebijakan ini dengan cermat sebelum " +
                            "And mengirimkan informasi apa pun ke situs web atau platform ini"
                )


                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("6. BERAPA LAMA KAMI MENYIMPAN INFORMASI ANDA?")
                PolicyParagraph(
                    "Kami menyimpan informasi selama yang diperlukan untuk menyediakan Platform " +
                            "dan untuk tujuan lain yang tercantum dalam Kebijakan Privasi ini, Kami juga " +
                            "menyimpan informasi jika diperlukan untuk memenuhi kewajiban kontraktual " +
                            "dan hukum, ketika kami memiliki kepentingan bisnis yang sah untuk " +
                            "melakukannya (seperti meningkatkan dan mengembangkan Platform, dan " +
                            "meningkatkan keamanan dan stabilitasnya), dan untuk pelaksanaan " +
                            "atau pembelaan klaim hukum."
                )
                PolicyParagraph(
                    "Periode penyimpanan berbeda. tergantung pada kriteria yang berbeda, " +
                            "seperti jenis informasi dan tujuan penggunaan Informasi oleh kami."
                )
                PolicyParagraph(
                    "Misalnya, ketika kami memproses Informasi Anda seperti informasi profil " +
                            "Anda untuk memberi Anda Layanan, kami menyimpan Informasi ini selama " +
                            "Anda memiliki akun di Platform. Jika Anda melanggar Persyaratan dan " +
                            "Ketentuan kami, atau ketentuan atau kebijakan lainnya, kami dapat " +
                            "menghentikan akun Anda atau membatasi akses ke Layanan kami, " +
                            "tetapi kami dapat menyimpan informasi lain tentang Anda untuk memproses " +
                            "pelanggaran tersebut."
                )

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("7. PRIVASI ANAK")
                PolicyParagraph(
                    "Kami tidak secara sengaja mengumpulkan informasi dari siapa pun " +
                            "yang berusia di bawah 18 tahun. Jika kami menemukan bahwa individu di " +
                            "bawah usia 18 tahun telah memberi kami informasi pribadi, kami akan " +
                            "segera menghapus Informasi Ini. Jika Anda adalah orang tua atau wali dan " +
                            "Anda mengetahui bahwa anak Anda telah memberi kami informasi pribadi " +
                            "tanpa izin Anda, silakan hubungi kami melalui bagian Hubungi Kami di bawah " +
                            "Ini."
                )

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("8. BAGAIMANA KAMI AKAN MEMBERI TAHU ANDA TENTANG PERUBAHAN PADA KEBIJAKAN PRIVASI INI?")
                PolicyParagraph(
                    "Kami dapat memperbarui Kebijakan Privasi ini dari waktu ke waktu: Kecuall " +
                            "jika diharuskan oleh hukum, kami akan memberi tahu Anda (misalnya, melalui " +
                            "Layanan kami) sebelum kami melakukan perubahan pada Kebijakan Privasi ini " +
                            "dan memberi Anda kesempatan untuk meninjau Kebijakan Privasi yang " +
                            "diperbarui sebelum Kebijakan Privasi yang diperbarul berlaku Akses " +
                            "berkelanjutan Anda ke atau penggunaan Layanan setelah tanggal " +
                            "Kebijakan Privasi yang diperbarui merupakan penerimaan Anda terhadap " +
                            "Kebijakan Privasi yang diperbarui. Jika Anda tidak setuju dengan Kebijakan " +
                            "Privasi yang diperbarui, Anda harus berhenti mengakses atau menggunakan " +
                            "Layanan,"
                )

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("9. BAHASA")
                PolicyParagraph("Kebijakan Privasi ini hanya tersedia dalam bahasa Indonesia.")

                Spacer(modifier = Modifier.height(16.dp))
                PolicyHeading("10. HUBUNGI KAMI")
                PolicyParagraph(
                    "Jika Anda memiliki pertanyaan, komentar, keluhan, atau permintaan " +
                            "terkait Kebijakan Privasi ini, silakan hubungi kami di " +
                            "privacy@bank.sampah"
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun PolicyHeading(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun PolicySubHeading(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
}

@Composable
fun PolicyParagraph(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun PolicyBullet(text: String) {
    Row(modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)) {
        Text(text = "•  ", style = MaterialTheme.typography.bodyMedium)
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}