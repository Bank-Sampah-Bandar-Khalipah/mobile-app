package com.example.nasabahcompose.ui.nasabah.components.deliver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasabahcompose.R

@Composable
fun LocationCardWithInput(
    currentAddress: String,
    onAddressChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Bagian atas - Home (Fixed)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home2),
                    contentDescription = "Home",
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Bandar Khalipah",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Desa Bandar Khalipah, Kecamatan Percut Sei Tuan, Kabupaten Deli Serdang",
                        fontSize = 12.sp,
                        color = Color(0xFF666666),
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Garis pembatas horizontal
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFFE0E0E0)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Bagian bawah - Lokasi saat ini dengan Input
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_loc),
                    contentDescription = "Location",
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Lokasi Anda saat ini",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Input Field untuk alamat
                    OutlinedTextField(
                        value = currentAddress,
                        onValueChange = onAddressChange,
                        placeholder = {
                            Text(
                                text = "Masukkan alamat lengkap Anda",
                                fontSize = 12.sp,
                                color = Color(0xFF9E9E9E)
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1A4AC3),
                            unfocusedBorderColor = Color(0xFFD0D0D0),
                            focusedContainerColor = Color(0xFFFAFAFA),
                            unfocusedContainerColor = Color(0xFFFAFAFA),
                            cursorColor = Color(0xFF1A4AC3)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 12.sp,
                            color = Color.Black
                        ),
                        minLines = 2,
                        maxLines = 3
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F2F2)
@Composable
fun PreviewLocationCardWithInput() {
    Box(modifier = Modifier.padding(16.dp)) {
        LocationCardWithInput(
            currentAddress = "",
            onAddressChange = {}
        )
    }
}