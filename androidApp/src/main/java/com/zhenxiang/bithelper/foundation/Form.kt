package com.zhenxiang.bithelper.foundation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.dokar.sheets.BottomSheet
import com.dokar.sheets.rememberBottomSheetState
import com.dsc.form_builder.TextFieldState
import com.zhenxiang.bithelper.component.BottomSheetContent
import com.zhenxiang.bithelper.form.TypedChoiceState
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.shared.res.SharedRes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormStateOutlinedTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    enabled: Boolean = true,
    label: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    transform: ((String) -> String)? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        value = state.value,
        enabled = enabled,
        isError = state.hasError,
        label = { Text(label) },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        onValueChange = if (transform != null) {
            { state.change(transform(it)) }
        } else {
            { state.change(it) }
        },
        keyboardOptions = keyboardOptions,
    )
}

@Composable
fun PasswordStateOutlinedTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    enabled: Boolean = true,
    label: String,
    imeAction: ImeAction = ImeAction.Default,
)  {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    FormStateOutlinedTextField(
        modifier = modifier,
        state = state,
        enabled = enabled,
        label = label,
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                if (passwordVisible) {
                    Icon(
                        imageVector = Icons.Outlined.VisibilityOff,
                        contentDescription = SharedRes.strings.hide_password.composeResource()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Visibility,
                        contentDescription = SharedRes.strings.show_password.composeResource()
                    )
                }
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
    )
}

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
