package com.zhenxiang.bithelper.ios.viewmodel

import com.zhenxiang.bithelper.ios.flow.FlowWrapper
import com.zhenxiang.bithelper.ios.flow.wrap
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class WithdrawPageBaseViewModel(
    val apiKeyId: Long,
    val assetTicker: String,
): CoroutineViewModel(), KoinComponent {

    private val repository: AccountDataRepository by inject()

    private val _assetBalanceFlow = MutableStateFlow<ResultWrapper<AssetBalance, ExchangeApiError>>(ResultWrapper.Loading())
    val assetBalanceFlow: FlowWrapper<ResultWrapper<AssetBalance, ExchangeApiError>> = _assetBalanceFlow.wrap()

    private val _withdrawMethodsFlow = MutableStateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>(
        ResultWrapper.Loading())
    val withdrawMethodsFlow: FlowWrapper<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>> = _withdrawMethodsFlow.wrap()

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

    fun withdraw() {
    }
}