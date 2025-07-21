package com.example.kmpplayground

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun GlassButton(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick, modifier = modifier) {
        Text(label)
    }
}