package com.zhenxiang.bithelper.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.form.DecimalStringTransformation
import com.zhenxiang.bithelper.form.state.*
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.utils.successDataOrNull
import kotlinx.coroutines.flow.StateFlow

abstract class WithdrawPageViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    abstract val assetTicker: String

    abstract val assetBalanceFlow: StateFlow<ResultWrapper<AssetBalance, ExchangeApiError>>

    abstract val withdrawMethodsFlow: StateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>

    val selectedWithdrawMethodState = derivedStateOf {
        formState.withdrawMethodId.value.let {
            val method = withdrawMethodsFlow.value.successDataOrNull()?.firstOrNull { item ->
                item.exchangeInternalId == it
            }
            method?.let {
                formState.amount.transformation = DecimalStringTransformation(it.decimalPrecision)
            }

            method
        }
    }

    val formState = Form()

    abstract fun withdraw()

    class Form: FormState() {

        val amount = TextFormFieldState(initialValidators = listOf(FormFieldValidator.Required))
        val recipientAddress = TextFormFieldState(
            initialValidators = listOf(FormFieldValidator.Required),
            initialTransformation = FormFieldValueTransformation.RemoveSpacesAndNewlines
        )
        val recipientMemo = TextFormFieldState()
        val withdrawMethodId = StringFormFieldState(initialValidators = listOf(FormFieldValidator.Required))

        override fun validate(): Boolean {
            val amountValid = amount.validate()
            val recipientAddressValid = recipientAddress.validate()
            val recipientMemoValid = recipientMemo.validate()
            val withdrawMethodIdValid = withdrawMethodId.validate()

            return amountValid && recipientAddressValid && withdrawMethodIdValid && recipientMemoValid
        }
    }

    companion object {
        const val API_KEY_ID_ARG = "apiKeyId"
        const val ASSET_TICKER_ARG = "assetTicker"
    }
}
