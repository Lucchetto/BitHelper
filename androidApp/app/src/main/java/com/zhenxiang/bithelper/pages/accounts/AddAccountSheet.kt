package com.zhenxiang.bithelper.pages.accounts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import com.zhenxiang.bithelper.form.component.FormStateOutlinedDropDownMenu
import com.zhenxiang.bithelper.form.component.FormStateOutlinedTextField
import com.zhenxiang.bithelper.shared.res.SharedRes
import com.zhenxiang.bithelper.ui.component.BottomSheetContent
import com.zhenxiang.bithelper.ui.foundation.*
import com.zhenxiang.bithelper.ui.utils.composeResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountSheet(navController: NavHostController, viewModel: AddAccountSheetViewModel) {

    val formState = remember { viewModel.formState }

    BottomSheetContent {
        TopAppBar(
            windowInsets = ZeroWindowInsets(),
            title = {
                Text(SharedRes.strings.add_account_sheet_title.composeResource())
            },
        )
        AutoFocus {
            FormStateOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(it),
                state = formState.label,
                label = SharedRes.strings.label_title.composeResource(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
        }
        FormStateOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = formState.apiKey,
            label = SharedRes.strings.api_key_title.composeResource(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                autoCorrect = false,
            ),
        )
        FormStateOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = formState.secretKey,
            label = SharedRes.strings.secret_key_title.composeResource(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                autoCorrect = false,
            ),
        )
        FormStateOutlinedDropDownMenu(
            label = SharedRes.strings.exchange_title.composeResource(),
            options = viewModel.exchanges,
            state = formState.exchange,
            toStringAdapter = {
                it.labelRes.composeResource()
            }
        )
        ButtonBar(
            modifier = Modifier.padding(top = MaterialTheme.spacing.level6)
        ) {
            Button(
                onClick = { if (viewModel.addAccount()) navController.popBackStack() }
            ) {
                Text(SharedRes.strings.add.composeResource())
            }
            TextButton(onClick = { navController.popBackStack() }) {
                Text(SharedRes.strings.cancel.composeResource())
            }
        }
    }
}
