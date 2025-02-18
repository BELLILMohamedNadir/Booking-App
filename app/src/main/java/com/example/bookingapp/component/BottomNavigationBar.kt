package com.example.bookingapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        painterResource(id = R.drawable.ic_home) to stringResource(id = R.string.home),
        painterResource(id = R.drawable.ic_search) to stringResource(id = R.string.reserve),
        painterResource(id = R.drawable.ic_flight) to stringResource(id = R.string.my_flights),
        painterResource(id = R.drawable.ic_flight_ticket) to stringResource(id = R.string.register),
        painterResource(id = R.drawable.ic_more) to stringResource(id = R.string.plus)
    )

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        containerColor = Color.White,) {
        items.forEachIndexed { index, (icon, description) ->
            NavigationBarItem(
                selected = (index == selectedItem),
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        modifier = Modifier.size(22.dp),
                        painter = icon,
                        contentDescription = description,
                        tint = if (index == selectedItem) colorResource(id = R.color.primary_color) else Color.Black.copy(alpha = 0.4f)
                    )
                },
                label = {
                    Text(
                        modifier = Modifier.offset(y = - (4).dp),
                        text = description,
                        color = if (index == selectedItem) colorResource(id = R.color.primary_color) else Color.Black.copy(alpha = 0.4f),
                        fontSize = (9.9).sp)},
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.primary_color),
                    unselectedIconColor = Color.Black.copy(alpha = 0.4f),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}