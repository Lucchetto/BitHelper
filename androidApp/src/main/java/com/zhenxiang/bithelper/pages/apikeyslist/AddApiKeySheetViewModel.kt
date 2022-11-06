package com.zhenxiang.bithelper.pages.apikeyslist

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.zhenxiang.bithelper.form.TypedChoiceState
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.repository.ApiKeysRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddApiKeySheetViewModel : ViewModel(), KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()
    val exchanges = Exchange.values().toList()

    val formState = FormState(
        fields = listOf(
            TextFieldState(
                name = LABEL_FORM_FIELD,
                validators = listOf(Validators.Required()),
            ),
            TextFieldState(
                name = API_KEY_FORM_FIELD,
                validators = listOf(Validators.Required()),
            ),
            TypedChoiceState<Exchange?>(
                initial = null,
                name = EXCHANGE_FORM_FIELD,
                validators = listOf(Validators.Required()),
            )
        )
    )

    fun addApiKey(): Boolean = if (formState.validate()) {
        apiKeysRepository.addApiKey(
            ApiKey(
                id = 0,
                apiKey = formState.getState<TextFieldState>(API_KEY_FORM_FIELD).value,
                exchange = formState.getState<TypedChoiceState<Exchange>>(EXCHANGE_FORM_FIELD).value,
                label = formState.getState<TextFieldState>(LABEL_FORM_FIELD).value,
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
        const val EXCHANGE_FORM_FIELD = "exchange"
    }
}