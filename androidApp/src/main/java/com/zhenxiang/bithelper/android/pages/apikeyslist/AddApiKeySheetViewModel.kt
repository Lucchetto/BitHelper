package com.zhenxiang.bithelper.android.pages.apikeyslist

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.zhenxiang.bithelper.android.form.getValueOfTextField
import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.db.StorageDb
import com.zhenxiang.bithelper.model.Exchange
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddApiKeySheetViewModel: ViewModel(), KoinComponent {

    private val storageDb: StorageDb by inject()

    val formState = FormState(
        fields = listOf(
            TextFieldState(
                name = LABEL_FORM_FIELD,
                validators = listOf(Validators.Required()),
            ),
            TextFieldState(
                name = API_KEY_FORM_FIELD,
                validators = listOf(Validators.Required()),
            )
        )
    )

    fun addApiKey(): Boolean = if (formState.validate()) {
        storageDb.apiKeyQueries.insert(
            ApiKey(
                apiKey = formState.getValueOfTextField(API_KEY_FORM_FIELD),
                exchange = Exchange.BINANCE,
                label = formState.getValueOfTextField(LABEL_FORM_FIELD),
                secretKey = null,
                creationTimestamp = System.currentTimeMillis(),
                readOnly = null,
            )
        )
        true
    } else {
        false
    }

    companion object {
        const val LABEL_FORM_FIELD = "label"
        const val API_KEY_FORM_FIELD = "apiKey"
    }
}