package com.zhenxiang.bithelper.pages.apikeyslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.zhenxiang.bithelper.foundation.FormStateOutlinedTextField
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.shared.res.SharedRes

@Composable
fun AddApiKeySheet(navController: NavController, viewModel: AddApiKeySheetViewModel) {

    val formState = remember { viewModel.formState }

    Column {
        FormStateOutlinedTextField(
            formState.getState(AddApiKeySheetViewModel.LABEL_FORM_FIELD),
            SharedRes.strings.label_title.composeResource()
        )
        FormStateOutlinedTextField(
            formState.getState(AddApiKeySheetViewModel.API_KEY_FORM_FIELD),
            SharedRes.strings.api_key_title.composeResource()
        )
        Row {
            Button(
                onClick = { if (viewModel.addApiKey()) navController.popBackStack() }
            ) {
                Text(SharedRes.strings.add.composeResource())
            }
            TextButton(onClick = { navController.popBackStack() }) {
                Text(SharedRes.strings.cancel.composeResource())
            }
        }
    }
}
