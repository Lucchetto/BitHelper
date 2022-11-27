package com.zhenxiang.bithelper.ios.viewmodel

import com.zhenxiang.bithelper.ios.flow.wrap
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

abstract class AccountDetailsPageBaseViewModel(val apiKeyId: Long): CoroutineViewModel(), KoinComponent {

    private val repository: AccountDataRepository by inject{ parametersOf(apiKeyId) }

    private val _accountBalances = MutableStateFlow<ResultWrapper<List<AssetBalance>, ExchangeApiError>>(ResultWrapper.Loading())
    protected val accountBalancesFlow = _accountBalances.wrap()

    protected val accountApiKeyFlow = repository.getApiKeyFlow(apiKeyId).onEach {
        if (it is ResultWrapper.Success) {
            _accountBalances.emit(repository.getBalances(it.data))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        ResultWrapper.Loading()
    ).wrap()
}