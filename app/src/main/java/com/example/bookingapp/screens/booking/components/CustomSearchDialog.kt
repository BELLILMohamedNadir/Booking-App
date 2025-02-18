package com.example.bookingapp.screens.booking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.bookingapp.R

@Composable
fun CustomSearchDialog(modifier: Modifier = Modifier,
                       label: String,
                       onDismiss: ()->Unit,
                       filteredStations: List<Pair<String, String>>,
                       query: MutableState<String>,
                       onClick: (String) -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss()},
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ){
        Column(
            modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(
                        contentColor = colorResource(id = R.color.green)
                    )) {
                        Icon(painter = painterResource(id = R.drawable.ic_train), contentDescription = "")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = label,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.green))
                }
                IconButton(onClick = { onDismiss() }, modifier = Modifier.size(36.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Gray
                    )) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)) {
                TextField(
                    value = query.value,
                    onValueChange = { query.value = it },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    },
                    label = { Text(stringResource(id = R.string.search)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.all_stations),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp))
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(filteredStations) { (station, code) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onClick(station)
                                }
                                .padding(8.dp)
                        ) {
                            IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(
                                contentColor = colorResource(id = R.color.gray_)
                            )) {
                                Icon(painter = painterResource(id = R.drawable.ic_train), contentDescription = "")
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = buildAnnotatedString {
                                        append(station)
                                        append("  ")
                                        withStyle(style = SpanStyle(color = colorResource(id = R.color.gray_), fontSize = 15.sp)) {
                                            append(code)
                                        }
                                    },
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "France",
                                    fontSize = 14.sp,
                                    color = colorResource(id = R.color.gray_),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}