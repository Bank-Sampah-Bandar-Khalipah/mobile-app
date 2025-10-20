package com.example.nasabahcompose.ui.admin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nasabahcompose.R

data class EditableWasteItem(
    val id: String,
    val name: String,
    val imageUrl: String,
    val initialPrice: String,
    val initialCategory: String
)

val dummyItems = listOf(
    EditableWasteItem("1", "Tutup Botol", "https://example.com/image.jpg", "2.000", "Plastik"),
    EditableWasteItem("2", "Tutup Botol", "https://example.com/image.jpg", "2.000", "Plastik"),
    EditableWasteItem("3", "Tutup Botol", "https://example.com/image.jpg", "2.000", "Plastik"),
    EditableWasteItem("4", "Tutup Botol", "https://example.com/image.jpg", "2.000", "Plastik"),
    EditableWasteItem("5", "Tutup Botol", "https://example.com/image.jpg", "2.000", "Plastik"),
    EditableWasteItem("6", "Tutup Botol", "https://example.com/image.jpg", "2.000", "Plastik"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditInformationScreen(
    username: String,
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Edit Informasi Sampah",
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Cari Sampah") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedBorderColor = Color(0xFF0B1D51),
                    unfocusedBorderColor = Color.Gray
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(dummyItems.filter { it.name.contains(searchQuery, ignoreCase = true) }) { item ->
                    EditableWasteCard(item = item)
                }
            }
        }
    }
}

@Composable
fun EditableWasteCard(item: EditableWasteItem) {
    var price by remember { mutableStateOf(item.initialPrice) }
    var category by remember { mutableStateOf(item.initialCategory) }

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = R.drawable.kardus2,
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Rp.", fontSize = 14.sp)
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    modifier = Modifier.weight(1f),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(8.dp)
                )
                Text("/kg", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Kategori", fontSize = 12.sp) },
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}