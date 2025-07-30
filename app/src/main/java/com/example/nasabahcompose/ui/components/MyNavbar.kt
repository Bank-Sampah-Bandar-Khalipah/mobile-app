package com.example.nasabahcompose.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nasabahcompose.R

@Composable
@Preview
fun MyNavbarBar(){
    val bottomMenuItemsList= prepareBottomMenu()
    val context= LocalContext.current
    var selected by remember { mutableStateOf("Home") }

    BottomAppBar (
        containerColor = colorResource(R.color.white),
        tonalElevation = 3.dp
    ) {
        bottomMenuItemsList.forEach { bottomMenuItems->
            BottomNavigationItem(
                selected=(selected == bottomMenuItems.label ),
                onClick = {
                    selected=bottomMenuItems.label
                        Toast.makeText(context, bottomMenuItems.label, Toast.LENGTH_SHORT).show()
                }, icon = {
                    Column(verticalArrangement = Arrangement.Center) {
                        Icon(painter = bottomMenuItems.icon,
                            contentDescription = null,
                            tint =  colorResource(R.color.black),
                            modifier = Modifier
                                .size(24.dp)
                            )
                    }
                }
            )

        }
    }
}

data class BottomMenuItem(
    val label:String, val icon:Painter
)

@Composable
fun prepareBottomMenu():List<BottomMenuItem>{

    val bottomMenuItemList=arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(BottomMenuItem(label = "Home", icon = painterResource(R.drawable.ic_home)))
    bottomMenuItemList.add(BottomMenuItem(label = "History", icon = painterResource(R.drawable.ic_history)))
    bottomMenuItemList.add(BottomMenuItem(label = "Ask", icon = painterResource(R.drawable.ic_ask)))
    bottomMenuItemList.add(BottomMenuItem(label = "User", icon = painterResource(R.drawable.ic_user)))

    return bottomMenuItemList
}
