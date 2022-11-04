package com.zhenxiang.bithelper.foundation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dsc.form_builder.TextFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormStateOutlinedTextField(state: TextFieldState, label: String) {
    OutlinedTextField(
        value = state.value,
        isError = state.hasError,
        label = { Text(label) },
        onValueChange = { state.change(it) }
    )
}
