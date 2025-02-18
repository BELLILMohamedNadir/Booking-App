package com.example.bookingapp.screens.booking.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R

@Composable
fun CustomCardView(
    modifier: Modifier = Modifier,
    text: String,
    headLabel: String,
    bodyLabel: String,
    shape: Shape = RoundedCornerShape(10.dp),
    fraction: Float = 0.95f,
    elevation: Dp = 2.dp,
    enabled: Boolean = true,
    onClickEvent: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(fraction = fraction)
            .clickable {
                if (enabled)
                    onClickEvent()
            },
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white))
    )  {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(text = headLabel,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
            Text(text = if(text.isEmpty() || text.isBlank()) bodyLabel else text,
                fontSize = 16.sp,
                color = if(text.isEmpty() || text.isBlank()) colorResource(id = R.color.black).copy(alpha = 0.4f) else colorResource(id = R.color.black)
            )
        }
    }
}