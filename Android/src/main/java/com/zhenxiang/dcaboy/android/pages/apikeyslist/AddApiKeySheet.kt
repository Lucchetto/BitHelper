package com.zhenxiang.dcaboy.android.pages.apikeyslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.zhenxiang.dcaboy.android.foundation.FormStateOutlinedTextField

@Composable
fun AddApiKeySheet(navController: NavController, viewModel: AddApiKeySheetViewModel) {

    val formState = remember { viewModel.formState }

    Column {
        FormStateOutlinedTextField(
            formState.getState(AddApiKeySheetViewModel.LABEL_FORM_FIELD),
            "Label"
        )
        FormStateOutlinedTextField(
            formState.getState(AddApiKeySheetViewModel.API_KEY_FORM_FIELD),
            "API key"
        )
        Row {
            Button(
                onClick = { if (viewModel.addApiKey()) navController.popBackStack() }
            ) {
                Text("Add")
            }
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Cancel")
            }
        }
    }
}
