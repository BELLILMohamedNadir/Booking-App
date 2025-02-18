package com.example.bookingapp.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.bookingapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(modifier: Modifier = Modifier, onDismiss: (Boolean) ->Unit) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.see_a_ride),
            color = colorResource(id = R.color.green),
            fontWeight = FontWeight.Bold
        ) },
        navigationIcon = {
            IconButton(onClick = { onDismiss(true) }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Back")
            }
        },
        actions = {
        }
    )
}