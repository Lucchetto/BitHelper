package com.zhenxiang.bithelper.foundation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dsc.form_builder.TextFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormStateOutlinedTextField(modifier: Modifier = Modifier, state: TextFieldState, label: String) {
    OutlinedTextField(
        modifier = modifier,
        value = state.value,
        isError = state.hasError,
        label = { Text(label) },
        onValueChange = { state.change(it) },
    )
}
