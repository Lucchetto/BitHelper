package com.zhenxiang.bithelper.pages.apikeyslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.zhenxiang.bithelper.foundation.*
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.shared.res.SharedRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddApiKeySheet(navController: NavController, viewModel: AddApiKeySheetViewModel) {

    val formState = remember { viewModel.formState }

    Column(
        modifier = Modifier.padding(ModalBottomSheetDefaults.contentPadding)
    ) {
        BottomSheetDragHandle()
        TopAppBar(
            title = {
                Text(SharedRes.strings.add_api_key_sheet_title.composeResource())
            },
        )
        FormStateOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = formState.getState(AddApiKeySheetViewModel.LABEL_FORM_FIELD),
            label = SharedRes.strings.label_title.composeResource()
        )
        FormStateOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = formState.getState(AddApiKeySheetViewModel.API_KEY_FORM_FIELD),
            label = SharedRes.strings.api_key_title.composeResource()
        )
        ButtonBar(
            modifier = Modifier.padding(top = MaterialTheme.spacing.level6)
        ) {
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
