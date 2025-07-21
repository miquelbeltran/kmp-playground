package com.example.kmpplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun GlassButton(modifier: Modifier, label: String, onClick: () -> Unit)
