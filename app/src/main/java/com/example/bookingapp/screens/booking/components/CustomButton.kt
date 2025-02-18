package com.example.bookingapp.screens.booking.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R

@Composable
fun CustomButton(modifier: Modifier = Modifier,
                 onCLick: () -> Unit) {
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp), contentAlignment = Alignment.Center){
        Button(
            onClick = onCLick,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.95f)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_color),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(36.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp, pressedElevation = 8.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(22.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        )
                    ) {
                        Icon(imageVector = Icons.Filled.Search , contentDescription =  "")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(modifier = Modifier.padding(2.dp), text = stringResource(id = R.string.search_flight), style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold), color = Color.White)
                }
                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }

}