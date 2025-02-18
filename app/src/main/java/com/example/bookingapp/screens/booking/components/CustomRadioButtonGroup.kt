package com.example.bookingapp.screens.booking.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R

@Composable
fun CustomRadioButtonGroup(
    modifier: Modifier = Modifier,
    onSelectOption: (String) -> Unit
) {
    val defaultOption = stringResource(id = R.string.one_way)
    var selectedOption by remember { mutableStateOf(defaultOption) }

    val options = listOf(stringResource(id = R.string.one_way), stringResource(id = R.string.round_trip))

    Row {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(8.dp),
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = {
                        selectedOption = option
                        onSelectOption(option)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorResource(id = R.color.primary_color),
                    ),
                    modifier = Modifier
                        .graphicsLayer(scaleX = 0.8f, scaleY = 0.8f)
                )
                Text(
                    text = option,
                    modifier = Modifier.offset(x = (-10).dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}