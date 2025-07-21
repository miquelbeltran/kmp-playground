package com.example.kmpplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun NativeToggle(modifier: Modifier, value: Boolean, onClick: (value: Boolean) -> Unit)