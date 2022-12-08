package com.zhenxiang.bithelper.pages.accounts

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.form.state.EnumFormFieldState
import com.zhenxiang.bithelper.form.state.FormFieldValidator
import com.zhenxiang.bithelper.form.state.FormState
import com.zhenxiang.bithelper.form.state.TextFormFieldState
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.repository.ApiKeysRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddAccountSheetViewModel : ViewModel(), KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()
    val exchanges = Exchange.values().toList()

    val formState = Form()

    fun addAccount(): Boolean = if (formState.validate()) {
        apiKeysRepository.addApiKey(
            ApiKey(
                id = 0,
                apiKey = formState.apiKey.value.text,
                exchange = formState.exchange.value!!,
                label = formState.label.value.text,
                secretKey = formState.secretKey.value.text,
                creationTimestamp = System.currentTimeMillis(),
                readOnly = null,
            )
        )
        true
    } else {
        false
    }

    class Form: FormState() {

        val label = TextFormFieldState(initialValidators = listOf(FormFieldValidator.Required))
        val apiKey = TextFormFieldState(initialValidators = listOf(FormFieldValidator.Required))
        val secretKey = TextFormFieldState(initialValidators = listOf(FormFieldValidator.Required))
        val exchange = EnumFormFieldState<Exchange?>(
            initialValue = null,
            initialValidators = listOf(FormFieldValidator.Required)
        )

        override fun validate(): Boolean {
            val labelValid = label.validate()
            val apiKeyValid = apiKey.validate()
            val secretKeyValid = secretKey.validate()
            val exchangeValid = exchange.validate()
            
            return labelValid && apiKeyValid && secretKeyValid && exchangeValid
        }
    }
}