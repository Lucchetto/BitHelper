package com.zhenxiang.bithelper.pages.accounts

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.zhenxiang.bithelper.form.CustomValidators
import com.zhenxiang.bithelper.form.TypedChoiceState
import com.zhenxiang.bithelper.form.ValidationMessages
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.repository.ApiKeysRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddAccountSheetViewModel : ViewModel(), KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()
    val exchanges = Exchange.values().toList()

    val formState = FormState(
        fields = listOf(
            TextFieldState(
                name = LABEL_FORM_FIELD,
                validators = listOf(CustomValidators.notBlank()),
            ),
            TextFieldState(
                name = API_KEY_FORM_FIELD,
                validators = listOf(CustomValidators.notBlank()),
            ),
            TextFieldState(
                name = SECRET_KEY_FORM_FIELD,
                validators = listOf(CustomValidators.notBlank()),
            ),
            TextFieldState(
                name = PASSWORD_FORM_FIELD,
            ),
            TypedChoiceState<Exchange>(
                initial = null,
                name = EXCHANGE_FORM_FIELD,
                validators = listOf(Validators.Required(ValidationMessages.REQUIRED)),
            )
        )
    )

    fun addAccount(): Boolean = if (formState.validate()) {
        apiKeysRepository.addApiKey(
            ApiKey(
                id = 0,
                apiKey = formState.getState<TextFieldState>(API_KEY_FORM_FIELD).value,
                exchange = formState.getState<TypedChoiceState<Exchange>>(EXCHANGE_FORM_FIELD).value!!,
                label = formState.getState<TextFieldState>(LABEL_FORM_FIELD).value,
                secretKey = formState.getState<TextFieldState>(SECRET_KEY_FORM_FIELD).value,
                password = formState.getState<TextFieldState>(PASSWORD_FORM_FIELD).value,
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
        const val SECRET_KEY_FORM_FIELD = "secretKey"
        const val PASSWORD_FORM_FIELD = "password"
        const val EXCHANGE_FORM_FIELD = "exchange"
    }
}