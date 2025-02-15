package com.example.fieldwise.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldwise.ui.theme.FieldWiseTheme

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick, // Use the provided onClick parameter
        modifier = modifier // Apply the modifier parameter
    ) {
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Close",
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CloseButtonPreview() {
    FieldWiseTheme {
        CloseButton(onClick = {  })
    }
}
