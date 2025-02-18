package com.example.bookingapp.screens.offers.components

import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomOfferCard(
    modifier: Modifier = Modifier,
    departStation: String,
    arrivedStation: String,
    isOneWay: Boolean = true,
    scrollIcon: ImageVector = Icons.Filled.ArrowForward,
    label: String,
    date: String,
    offers: List<Pair<String, String>>,
    onCardClick: () -> Unit,
    onRoundTripClick: () -> Unit) {

    val listState = rememberLazyListState()

    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white))
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = colorResource(id = R.color.blue)
                        ), modifier = Modifier.size(48.dp)) {
                        Icon(painter = painterResource(id = R.drawable.ic_train), contentDescription = "")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = label,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.blue)
                    )
                }
                if (!isOneWay){
                    Card(
                        modifier = Modifier
                            .padding(vertical = 2.dp, horizontal = 5.dp)
                            .border(
                                BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(50.dp)
                            ),
                        shape = RoundedCornerShape(50.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white))
                    ){
                        IconButton(onClick = { onRoundTripClick()
                        }, modifier = Modifier.size(32.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = colorResource(id = R.color.primary_color)
                            )) {
                            Icon(imageVector = scrollIcon, contentDescription = "Back")
                        }
                    }
                }
            }
            Text(text = buildString {
                append(departStation)
                append(" - ")
                append(arrivedStation)
            },
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 15.dp))
            Spacer(modifier = Modifier.height(14.dp))

            // Body
            LazyColumn(
                state = listState
                ,modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)) {

                itemsIndexed(offers){ index ,(depart, arrived) ->
                    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                    val departTime = LocalTime.parse(depart, timeFormatter)
                    val arrivedTime = LocalTime.parse(arrived, timeFormatter)

                    val duree = Duration.between(departTime, arrivedTime).toMinutes()
                    CustomTimeCard(
                        depart = depart,
                        arrived = arrived,
                        duree = "$duree",
                        index = index + 1) {
                        onCardClick()
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}