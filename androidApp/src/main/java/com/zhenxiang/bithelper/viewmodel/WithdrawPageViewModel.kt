package com.zhenxiang.bithelper.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.zhenxiang.bithelper.form.CustomValidators
import com.zhenxiang.bithelper.form.TypedChoiceState
import com.zhenxiang.bithelper.form.ValidationMessages
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import kotlinx.coroutines.flow.StateFlow

abstract class WithdrawPageViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    abstract val assetTicker: String

    abstract val withdrawMethodsFlow: StateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>

    val formState = FormState(listOf(
        TextFieldState(
            name = RECIPIENT_ADDRESS_FORM_FIELD,
            validators = listOf(CustomValidators.notBlank()),
        ),
        TextFieldState(
            name = RECIPIENT_ADDRESS_MEMO_FORM_FIELD
        ),
        TypedChoiceState<WithdrawMethod?>(
            initial = null,
            name = WITHDRAW_METHOD_FORM_FIELD,
            validators = listOf(Validators.Required(ValidationMessages.REQUIRED)),
        )
    ))

    abstract fun withdraw()

    companion object {
        const val API_KEY_ID_ARG = "apiKeyId"
        const val ASSET_TICKER_ARG = "assetTicker"

        const val RECIPIENT_ADDRESS_FORM_FIELD = "receivingAddress"
        const val RECIPIENT_ADDRESS_MEMO_FORM_FIELD = "receivingAddressMemo"
        const val WITHDRAW_METHOD_FORM_FIELD = "method"
    }
}
