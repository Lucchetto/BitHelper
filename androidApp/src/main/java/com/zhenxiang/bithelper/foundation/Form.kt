package com.zhenxiang.bithelper.foundation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.dokar.sheets.BottomSheet
import com.dokar.sheets.rememberBottomSheetState
import com.dsc.form_builder.TextFieldState
import com.zhenxiang.bithelper.component.BottomSheetContent
import com.zhenxiang.bithelper.form.DecimalStringTransform
import com.zhenxiang.bithelper.form.TypedChoiceState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormStateOutlinedTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    transform: ((String) -> String?)? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        value = state.value,
        enabled = enabled,
        readOnly = readOnly,
        isError = state.hasError,
        textStyle = textStyle,
        label = { Text(label) },
        visualTransformation = visualTransformation,
        onValueChange = if (transform != null) {
            { newValue -> transform(newValue)?.let { state.change(it) } }
        } else {
            { state.change(it) }
        },
        keyboardOptions = keyboardOptions,
    )
}

@Composable
fun DecimalFormStateOutlinedTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    precision: Int,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeAction: ImeAction = ImeAction.Default,
) = FormStateOutlinedTextField(
    modifier = modifier,
    state = state,
    enabled = enabled,
    readOnly = readOnly,
    textStyle = TextStyle(textAlign = TextAlign.End),
    label = label,
    visualTransformation = visualTransformation,
    keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Decimal,
        imeAction = imeAction
    ),
    transform = { DecimalStringTransform.transform(it, precision) }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FormStateOutlinedDropDownMenu(
    modifier: Modifier = Modifier,
    state: TypedChoiceState<T>,
    enabled: Boolean = true,
    label: String,
    options: List<T>,
    toStringAdapter: @Composable (T) -> String,
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = enabled && !expanded
        }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .onFocusChanged {
                    if (it.isFocused && enabled) {
                        expanded = true
                    }
                }
                .fillMaxWidth(),
            enabled = enabled,
            readOnly = true,
            value = state.value?.let { toStringAdapter(it) } ?: "",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FormStateOutlinedBottomSheetMenu(
    modifier: Modifier = Modifier,
    state: TypedChoiceState<T>,
    enabled: Boolean = true,
    label: String,
    options: List<T>,
    toStringAdapter: @Composable (T) -> String,
    trailingIconOverride: @Composable (() -> Unit)? = null,
    bottomSheetListItem: @Composable (ColumnScope.(selected: Boolean, clicked: () -> Unit, value: T) -> Unit),
) {

    val scope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    scope.launch { sheetState.expand() }
                    focusManager.clearFocus()
                }
            }
            .fillMaxWidth(),
        enabled = enabled,
        readOnly = true,
        value = state.value?.let { toStringAdapter(it) } ?: "",
        onValueChange = { },
        isError = state.hasError,
        label = { Text(label) },
        trailingIcon = trailingIconOverride ?: {
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = sheetState.visible)
        },
        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
    )

    BottomSheet(
        state = sheetState,
        shape = ModalBottomSheetDefaults.shape,
        skipPeek = true,
        dragHandle = { },
        backgroundColor = MaterialTheme.colorScheme.surface,
    ) {
        BottomSheetContent {
            TopAppBar(
                windowInsets = ZeroWindowInsets(),
                title = { Text(label) },
            )
            options.forEach {

                val clicked = {
                    state.change(it)
                    scope.launch { sheetState.collapse() }
                    Unit
                }

                bottomSheetListItem(state.value == it, clicked, it)
            }
        }
    }
}
