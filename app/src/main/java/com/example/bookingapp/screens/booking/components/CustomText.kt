package com.example.bookingapp.screens.booking.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(16.dp)
            .offset(x = 5.dp),
        color = colorResource(id = R.color.primary_color),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center
        )
}