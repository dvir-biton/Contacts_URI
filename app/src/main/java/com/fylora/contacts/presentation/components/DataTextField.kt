package com.fylora.contacts.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.fylora.contacts.presentation.ui.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = fontFamily
        ),
        placeholder = {
            Text(
                text = hint,
                fontFamily = fontFamily
            )
        },
        shape = RoundedCornerShape(16.dp)
    )
}