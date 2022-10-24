package com.zhenxiang.dcaboy.android.foundation

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.TextFieldState

@Composable
fun FormStateOutlinedTextField(state: TextFieldState, label: String) {
    OutlinedTextField(
        value = state.value,
        isError = state.hasError,
        label = { Text(label) },
        onValueChange = { state.change(it) }
    )
}
