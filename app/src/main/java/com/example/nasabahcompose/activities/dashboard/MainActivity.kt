package com.example.nasabahcompose.activities.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.Trash
import com.example.nasabahcompose.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Dummy data untuk ditampilkan di HomeScreen
        val dummyTrashList = listOf(
            Trash("Botol Plastik", 2000, R.drawable.sampah, "Plastik"),
            Trash("Kertas", 1000, R.drawable.sampah, "Kertas"),
            Trash("Kardus", 1500, R.drawable.sampah, "Kardus")
        )

        setContent {
            HomeScreen(trashList = dummyTrashList)
        }
    }
}
