package com.example.bookingapp.screens.offers

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import com.example.bookingapp.component.BottomNavigationBar
import com.example.bookingapp.screens.offers.components.CustomOfferCard
import com.example.bookingapp.component.TopNavigationBar

@SuppressLint("SuspiciousIndentation")
@Composable
fun OfferScreen(modifier: Modifier = Modifier,
                departStation:String,
                arrivedStation:String,
                departDate :String,
                arrivedDate :String = "",
                isOneWay: Boolean,
                onDismiss: (Boolean) -> Unit) {

    val isShowOneWayCard = remember {
        mutableStateOf(true)
    }
    val isReservationDone = remember {
        mutableStateOf(false)
    }

    val offers = listOf(
        "10:30" to "12:50",
        "12:30" to "15:40",
        "01:20" to "08:10",
        "01:30" to "12:50",
        "10:45" to "13:00",
        "11:00" to "13:20",
        "12:00" to "14:30",
        "14:00" to "16:00",
        "16:30" to "19:00",
        "18:30" to "20:00",
        "20:00" to "22:00"
    )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopNavigationBar(){
                if (isReservationDone.value){
                    isReservationDone.value = false
                }else{
                    onDismiss(true)
                }
            } },
            bottomBar = { BottomNavigationBar() }
        ) {padding ->
            if (!isReservationDone.value){
                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(padding)
                        .background(color = colorResource(id = R.color.beg)) ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ){
                    Spacer(modifier = Modifier.height(5.dp))

                    if (isShowOneWayCard.value){
                        CustomOfferCard(
                            departStation = departStation,
                            arrivedStation = arrivedStation,
                            label = stringResource(id = R.string.depart_ride),
                            date = departDate,
                            isOneWay = isOneWay,
                            offers = offers,
                            onCardClick = {
                                isReservationDone.value = true
                            }){
                            if (!isOneWay){
                                isShowOneWayCard.value = false
                            }
                        }
                    }

                    if (!isOneWay && !isShowOneWayCard.value) {
                        CustomOfferCard(
                            departStation = arrivedStation,
                            arrivedStation = departStation,
                            isOneWay = isOneWay,
                            scrollIcon = Icons.Filled.ArrowBack,
                            label = stringResource(id = R.string.arrived_ride),
                            date = arrivedDate,
                            offers = offers,
                            onCardClick = {
                                isReservationDone.value = true
                            }
                        ){
                            isShowOneWayCard.value = true
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }else{
                Column(
                    modifier = Modifier
                        .fillMaxSize() ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier.size(100.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = colorResource(id = R.color.green)
                        )) {
                        Icon(painter = painterResource(id = R.drawable.ic_done), contentDescription = "done")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.reservation_success),
                        fontSize = 22.sp
                    )
                }
            }
        }

}