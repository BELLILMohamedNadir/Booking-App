package com.example.bookingapp.screens.booking

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookingapp.R
import com.example.bookingapp.component.BottomNavigationBar
import com.example.bookingapp.screens.booking.components.CustomButton
import com.example.bookingapp.screens.booking.components.CustomCardView
import com.example.bookingapp.component.CustomDatePickerDialog
import com.example.bookingapp.screens.booking.components.CustomPlaceSelector
import com.example.bookingapp.screens.booking.components.CustomRadioButtonGroup
import com.example.bookingapp.screens.booking.components.CustomText
import com.example.bookingapp.screens.offers.OfferScreen
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
@Composable
fun BookingScreen(
    modifier: Modifier = Modifier,
    context: Context
) {
    val isDatePickerDialogOpen = remember { mutableStateOf(false) }
    val arrivedDate = remember { mutableStateOf("") }
    val departDate = remember { mutableStateOf("") }
    val isDepartDate = remember { mutableStateOf(false) }
    val isArrivedDate = remember { mutableStateOf(false) }
    val isShowBooking = remember { mutableStateOf(true) }
    val selectedOption = remember { mutableStateOf("") }
    val departStation = remember { mutableStateOf("") }
    val arrivedStation = remember { mutableStateOf("") }

    val oneWay = stringResource(id = R.string.one_way)
    val stations = listOf(
        "Gare de Lyon" to "PAR",
        "Gare du Nord" to "PAR",
        "Gare Saint-Lazare" to "PAR",
        "Gare de l'Est" to "PAR",
        "Gare Montparnasse" to "PAR",
        "Gare d'Austerlitz" to "PAR",
        "Gare de Bercy" to "PAR",
        "Gare de Massy TGV" to "PAR",
        "Gare de Marne-la-Vallée - Chessy" to "PAR",
        "Gare de Lyon Part-Dieu" to "LYS",
        "Gare de Lyon Perrache" to "LYS",
        "Gare de Marseille-Saint-Charles" to "MRS",
        "Gare de Bordeaux-Saint-Jean" to "BOD",
        "Gare de Lille Flandres" to "LIL",
        "Gare de Lille Europe" to "LIL",
        "Gare de Strasbourg" to "SXB",
        "Gare de Rennes" to "RNS",
        "Gare de Nantes" to "NTE",
        "Gare de Toulouse-Matabiau" to "TLS",
        "Gare de Montpellier Saint-Roch" to "MPL",
        "Gare de Nice-Ville" to "NCE",
        "Gare de Grenoble" to "GNB",
        "Gare de Dijon-Ville" to "DIJ",
        "Gare d'Avignon TGV" to "AVN",
        "Gare de Rouen-Rive-Droite" to "URO",
        "Gare de Reims" to "RHE",
        "Gare de Clermont-Ferrand" to "CFE",
        "Gare de Metz-Ville" to "ETZ",
        "Gare de Nancy" to "ENC",
        "Gare de Le Mans" to "LME",
        "Gare de Tours" to "TUF",
        "Gare de Poitiers" to "PIS",
        "Gare de Besançon Viotte" to "BSL",
        "Gare de Brest" to "BES",
        "Gare de La Rochelle" to "LRH",
        "Gare de Saint-Étienne Châteaucreux" to "EBU",
        "Gare de Valence TGV" to "VAF",
        "Gare de Perpignan" to "PGF",
        "Gare de Bayonne" to "XBY"
    )
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = dateFormat.format(Calendar.getInstance().time)

    selectedOption.value = oneWay
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) {paddingValues ->
        if (isShowBooking.value){
            Column(modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)) {

                Spacer(modifier = Modifier.height(5.dp))
                CustomText(text = stringResource(id = R.string.where_you_want_to_go))
                Spacer(modifier = Modifier.height(2.dp))
                CustomRadioButtonGroup() { option ->
                    selectedOption.value = option
                }
                Spacer(modifier = Modifier.height(1.dp))
                CustomPlaceSelector(
                    stations = stations,
                    onPlaceSelected = { depart, arrived ->
                        departStation.value =depart
                        arrivedStation.value =arrived
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (selectedOption.value == stringResource(id = R.string.round_trip))
                            CustomCardView(
                                text = departDate.value,
                                headLabel = stringResource(id = R.string.depart),
                                bodyLabel = stringResource(id = R.string.depart_date),
                                shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                                fraction = 1f,
                                onClickEvent = {
                                    isDatePickerDialogOpen.value = true
                                    isDepartDate.value = true
                                    isArrivedDate.value = false
                                }
                            )
                        else
                            CustomCardView(
                                text = departDate.value,
                                headLabel = stringResource(id = R.string.depart),
                                bodyLabel = stringResource(id = R.string.depart_date),
                                shape = RoundedCornerShape(
                                    topEnd = 10.dp,
                                    topStart = 10.dp,
                                    bottomStart = 10.dp,
                                    bottomEnd = 10.dp
                                ),
                                fraction = 1f,
                                onClickEvent = {
                                    isDatePickerDialogOpen.value = true
                                    isDepartDate.value = true
                                    isArrivedDate.value = false
                                }
                            )
                    }
                    if (selectedOption.value == stringResource(id = R.string.round_trip))
                        Box(modifier = Modifier.weight(1f)) {
                            CustomCardView(
                                text = arrivedDate.value,
                                headLabel = stringResource(id = R.string.arrived),
                                bodyLabel = stringResource(id = R.string.arrived_date),
                                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
                                fraction = 1f,
                                enabled = selectedOption.value == stringResource(id = R.string.round_trip),
                                onClickEvent = {
                                    isDatePickerDialogOpen.value = true
                                    isDepartDate.value = false
                                    isArrivedDate.value = true
                                })
                        }
                }
                val fillGapMessage = stringResource(id = R.string.fill_gap)
                val selectDifferentMessage = stringResource(id = R.string.different_destination)
                val dateCriteriaMessage = stringResource(id = R.string.arrived_date_bigger_than_depart_date)
                val departDateBiggerThanDepartDateMessage = stringResource(id = R.string.depart_date_bigger_than_current_date)
                CustomButton {
                    if ((departStation.value.isEmpty() || departStation.value.isBlank()) ||
                    (arrivedStation.value.isEmpty() || arrivedStation.value.isBlank())){
                        Toast.makeText(context, fillGapMessage, Toast.LENGTH_SHORT).show()
                    }else{
                        if (departDate.value < currentDate){
                            Toast.makeText(context, departDateBiggerThanDepartDateMessage, Toast.LENGTH_SHORT).show()
                        }else{
                            if (selectedOption.value == oneWay){
                                if (departDate.value.isEmpty() || departDate.value.isBlank()){
                                    Toast.makeText(context, fillGapMessage, Toast.LENGTH_SHORT).show()
                                }else{
                                    if (departStation.value == arrivedStation.value)
                                        Toast.makeText(context, selectDifferentMessage, Toast.LENGTH_SHORT).show()
                                    else{
                                        isShowBooking.value = false
                                    }
                                }
                            }else{
                                if ((departDate.value.isEmpty() || departDate.value.isBlank())
                                    &&
                                    arrivedDate.value.isEmpty() || arrivedDate.value.isBlank()){
                                    Toast.makeText(context, fillGapMessage, Toast.LENGTH_SHORT).show()
                                }else{
                                    if (departStation.value == arrivedStation.value)
                                        Toast.makeText(context, selectDifferentMessage, Toast.LENGTH_SHORT).show()
                                    else{
                                        if (departDate.value > arrivedDate.value)
                                            Toast.makeText(context, dateCriteriaMessage, Toast.LENGTH_SHORT).show()
                                        else
                                            isShowBooking.value = false
                                    }
                                }
                            }
                        }
                    }
                }
                if (isDatePickerDialogOpen.value) {
                    CustomDatePickerDialog(
                        onDateSelected = { miles ->
                            val date = miles?.let { Date(it) }
                            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                            if (isDepartDate.value) {
                                departDate.value = date?.let { format.format(it) }.toString()
                            } else {
                                arrivedDate.value = date?.let { format.format(it) }.toString()
                            }
                        }
                    ) {
                        isDatePickerDialogOpen.value = false
                    }
                }

            }
        }else{
            OfferScreen(
                modifier = Modifier.padding(16.dp),
                departStation = departStation.value,
                arrivedStation = arrivedStation.value,
                departDate = departDate.value,
                arrivedDate = arrivedDate.value,
                isOneWay = selectedOption.value == oneWay
            ){
                isShowBooking.value = true
                arrivedDate.value = ""
                departDate.value = ""
                isDepartDate.value = false
                isArrivedDate.value = false
                departStation.value = ""
                arrivedStation.value = ""
            }

        }
    }

}