package com.zhenxiang.bithelper.foundation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import com.dsc.form_builder.TextFieldState
import com.zhenxiang.bithelper.form.TypedChoiceState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormStateOutlinedTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    transform: ((String) -> String)? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        value = state.value,
        isError = state.hasError,
        label = { Text(label) },
        onValueChange = if (transform != null) {
            { state.change(transform(it)) }
        } else {
            { state.change(it) }
        },
        keyboardOptions = keyboardOptions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FormStateOutlinedDropDownMenu(
    modifier: Modifier = Modifier,
    state: TypedChoiceState<T>,
    label: String,
    options: List<T>,
    toStringAdapter: @Composable (T) -> String,
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .onFocusChanged {
                    if (it.isFocused) {
                        expanded = true
                    }
                }
                .fillMaxWidth(),
            readOnly = true,
            value = toStringAdapter(state.value),
            onValueChange = { },
            isError = state.hasError,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(text = toStringAdapter(selectionOption))
                    },
                    onClick = {
                        state.change(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}
