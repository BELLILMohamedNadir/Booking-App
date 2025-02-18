package com.example.bookingapp.screens.booking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookingapp.R

@Composable
fun CustomPlaceSelector(stations: List<Pair<String, String>>, onPlaceSelected: (String, String) -> Unit) {
    val query = remember { mutableStateOf("") }
    var depart by remember { mutableStateOf("") }
    var arrived by remember { mutableStateOf("") }
    val isDialogOpen = remember { mutableStateOf(false) }
    val isDepart = remember { mutableStateOf(false) }
    val isArrived = remember { mutableStateOf(false) }
    val filteredPlaces = stations.filter { (station, code) -> station.contains(query.value, ignoreCase = true) }

    Column(modifier = Modifier.padding(16.dp)) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Column {
                CustomCardView(text = depart, headLabel = stringResource(id = R.string.de), bodyLabel = stringResource(
                    id = R.string.depart_station), shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                    , onClickEvent = {
                    isDialogOpen.value = true
                    isDepart.value = true
                    isArrived.value = false
                })
                Spacer(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(fraction = 0.95f)
                    .background(color = colorResource(id = R.color.black).copy(alpha = 0.1f)))
                CustomCardView(text = arrived, headLabel = stringResource(id = R.string.à), bodyLabel = stringResource(
                    id = R.string.arrived_station), shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                    , onClickEvent = {
                    isDialogOpen.value = true
                    isDepart.value = false
                    isArrived.value = true
                })
            }
            Box(modifier = Modifier
                .background(color = colorResource(id = R.color.black).copy(alpha = 0f))
                .align(Alignment.CenterEnd)
                .offset(x = (-25).dp)){
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.gray),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(4.dp)
                        .align(Alignment.CenterEnd)
                        .size(20.dp),
                    contentAlignment = Alignment.CenterEnd,
                ){
                    IconButton(
                        onClick = {
                            val s = depart
                            depart = arrived
                            arrived = s
                        },
                        enabled = depart.isNotEmpty() && arrived.isNotEmpty()
                        ,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = if (depart.isNotEmpty() && arrived.isNotEmpty())
                                colorResource(id = R.color.primary_color)
                            else
                                colorResource(id = R.color.black)
                        )
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_change), contentDescription = "")
                    }
                }
            }
        }

        if (isDialogOpen.value) {
            CustomSearchDialog(
                label =
                if (isDepart.value)
                    stringResource(id = R.string.depart_station)
                else
                    stringResource(id = R.string.arrived_station),
                filteredStations = filteredPlaces, query = query, onClick =  { place ->
                if (isDepart.value)
                    depart = place
                else
                    arrived = place
                query.value = ""
                isDialogOpen.value = false
                onPlaceSelected(depart, arrived)
            }, onDismiss = {
                isDialogOpen.value = false
            })
        }
    }
}