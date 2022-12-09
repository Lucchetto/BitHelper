package com.zhenxiang.bithelper.viewmodel.impl

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.zhenxiang.bithelper.form.DecimalStringTransformation
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.model.WithdrawRequest
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import com.zhenxiang.bithelper.shared.utils.nullIfBlank
import com.zhenxiang.bithelper.utils.successDataOrNull
import com.zhenxiang.bithelper.viewmodel.WithdrawPageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class WithdrawPageViewModelImpl(
    savedStateHandle: SavedStateHandle,
    private val repository: AccountDataRepository,
): WithdrawPageViewModel(savedStateHandle) {

    private val apiKeyId = savedStateHandle.get<Long>(API_KEY_ID_ARG)!!
    override val assetTicker = savedStateHandle.get<String>(ASSET_TICKER_ARG)!!

    private val _assetBalanceFlow = MutableStateFlow<ResultWrapper<AssetBalance, ExchangeApiError>>(ResultWrapper.Loading())
    override val assetBalanceFlow: StateFlow<ResultWrapper<AssetBalance, ExchangeApiError>> = _assetBalanceFlow

    private val _withdrawMethodsFlow = MutableStateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>(ResultWrapper.Loading())
    override val withdrawMethodsFlow: StateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>> = _withdrawMethodsFlow

    override val selectedWithdrawMethodFlow: StateFlow<WithdrawMethod?> =
        snapshotFlow { formState.withdrawMethodId.value }.combine(withdrawMethodsFlow) { id, methodsResult ->
            methodsResult.successDataOrNull()?.firstOrNull { it.exchangeInternalId == id }?.also {
                formState.amount.transformation = DecimalStringTransformation(it.decimalPrecision)
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val accountApiKeyFlow = repository.getApiKeyFlow(apiKeyId).onEach {
        if (it is ResultWrapper.Success) {
            _assetBalanceFlow.emit(repository.getAssetBalance(it.data, assetTicker))
            _withdrawMethodsFlow.emit(repository.getAssetWithdrawMethods(it.data, assetTicker))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        ResultWrapper.Loading()
    )

    override fun withdraw() {
        val apiKey = accountApiKeyFlow.value.successDataOrNull()
        if (apiKey != null && formState.validate()) {
            viewModelScope.launch(Dispatchers.IO) {
                formState.apply {
                    repository.withdraw(
                        apiKey,
                        WithdrawRequest(
                            assetTicker,
                            recipientAddress.value.text,
                            recipientMemo.value.text.nullIfBlank(),
                            BigDecimal.parseString(amount.value.text)
                        )
                    )
                }
            }
        }
    }
}