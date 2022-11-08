package com.zhenxiang.bithelper.pages.apikeyslist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.zhenxiang.bithelper.foundation.*
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.navigation.RootNavigationSheet
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.res.SharedRes
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.pop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddApiKeySheet(sheetController: NavController<RootNavigationSheet>, viewModel: AddApiKeySheetViewModel) {

    val formState = remember { viewModel.formState }

    Column(
        modifier = Modifier
            .padding(ModalBottomSheetDefaults.contentPadding)
            .verticalScroll(rememberScrollState())
            .systemBarsPadding()
            .imePadding()
    ) {
        BottomSheetDragHandle()
        TopAppBar(
            windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
            title = {
                Text(SharedRes.strings.add_api_key_sheet_title.composeResource())
            },
        )
        AutoFocus {
            FormStateOutlinedTextField(
                modifier = Modifier.fillMaxWidth().focusRequester(it),
                state = formState.getState(AddApiKeySheetViewModel.LABEL_FORM_FIELD),
                label = SharedRes.strings.label_title.composeResource(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
        }
        FormStateOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = formState.getState(AddApiKeySheetViewModel.API_KEY_FORM_FIELD),
            label = SharedRes.strings.api_key_title.composeResource(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                autoCorrect = false,
            )
        )
        FormStateOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = formState.getState(AddApiKeySheetViewModel.SECRET_KEY_FORM_FIELD),
            label = SharedRes.strings.secret_key_title.composeResource(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                autoCorrect = false,
            )
        )
        FormStateOutlinedDropDownMenu<Exchange?>(
            label = SharedRes.strings.exchange_title.composeResource(),
            options = viewModel.exchanges,
            state = formState.getState(AddApiKeySheetViewModel.EXCHANGE_FORM_FIELD),
            toStringAdapter = {
                it?.labelRes?.composeResource() ?: ""
            }
        )
        ButtonBar(
            modifier = Modifier.padding(top = MaterialTheme.spacing.level6)
        ) {
            Button(
                onClick = { if (viewModel.addApiKey()) sheetController.pop() }
            ) {
                Text(SharedRes.strings.add.composeResource())
            }
            TextButton(onClick = { sheetController.pop() }) {
                Text(SharedRes.strings.cancel.composeResource())
            }
        }
    }
}
