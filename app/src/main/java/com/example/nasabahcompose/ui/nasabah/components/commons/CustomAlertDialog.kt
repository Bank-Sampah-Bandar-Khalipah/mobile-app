package com.example.nasabahcompose.ui.nasabah.components.commons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

/**
 * Custom Alert Dialog yang bisa digunakan di berbagai halaman
 *
 * @param showDialog State untuk menampilkan/menyembunyikan dialog
 * @param onDismiss Callback saat dialog ditutup
 * @param title Judul dialog
 * @param message Pesan dialog
 * @param confirmText Text untuk tombol konfirmasi (default: "OK")
 * @param onConfirm Callback saat tombol konfirmasi ditekan (optional)
 * @param showCancelButton Tampilkan tombol batal atau tidak (default: false)
 * @param cancelText Text untuk tombol batal (default: "Batal")
 */
@Composable
fun CustomAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    title: String,
    message: String,
    confirmText: String = "OK",
    onConfirm: (() -> Unit)? = null,
    showCancelButton: Boolean = false,
    cancelText: String = "Batal"
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0A2B6E),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Message
                    Text(
                        text = message,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (showCancelButton)
                            Arrangement.spacedBy(12.dp)
                        else
                            Arrangement.Center
                    ) {
                        // Cancel Button (jika showCancelButton = true)
                        if (showCancelButton) {
                            OutlinedButton(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                shape = RoundedCornerShape(24.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color(0xFF0A2B6E)
                                )
                            ) {
                                Text(
                                    text = cancelText,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        // Confirm Button
                        Button(
                            onClick = {
                                onConfirm?.invoke() ?: onDismiss()
                            },
                            modifier = Modifier
                                .then(
                                    if (showCancelButton) Modifier.weight(1f)
                                    else Modifier.widthIn(min = 120.dp)
                                )
                                .height(48.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0A2B6E)
                            )
                        ) {
                            Text(
                                text = confirmText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Varian untuk peringatan (warning)
 */
@Composable
fun WarningAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    title: String = "Perhatian!",
    message: String,
    confirmText: String = "Mengerti"
) {
    CustomAlertDialog(
        showDialog = showDialog,
        onDismiss = onDismiss,
        title = title,
        message = message,
        confirmText = confirmText,
        showCancelButton = false
    )
}

/**
 * Varian untuk konfirmasi (confirmation)
 */
@Composable
fun ConfirmationAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String = "Konfirmasi",
    message: String,
    confirmText: String = "Ya",
    cancelText: String = "Tidak"
) {
    CustomAlertDialog(
        showDialog = showDialog,
        onDismiss = onDismiss,
        title = title,
        message = message,
        confirmText = confirmText,
        onConfirm = onConfirm,
        showCancelButton = true,
        cancelText = cancelText
    )
}