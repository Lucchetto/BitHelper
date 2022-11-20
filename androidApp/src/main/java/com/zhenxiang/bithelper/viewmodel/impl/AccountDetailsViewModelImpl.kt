package com.zhenxiang.bithelper.viewmodel.impl

import androidx.lifecycle.viewModelScope
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.provider.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import kotlinx.coroutines.flow.*

internal class AccountDetailsViewModelImpl(
    repository: AccountDataRepository
) : AccountDetailsViewModel() {

    private val _accountBalances = MutableStateFlow<ResultWrapper<List<AssetBalance>, ExchangeApiError>>(ResultWrapper.Loading())
    override val accountBalancesFlow: StateFlow<ResultWrapper<List<AssetBalance>, ExchangeApiError>> = _accountBalances

    private val _accountApiKeyFlow = repository.apiKeyFlow.onEach {
        if (it is ResultWrapper.Success) {
            _accountBalances.emit(repository.getBalances(it.data))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        ResultWrapper.Loading()
    )

    override val accountApiKeyFlow: StateFlow<ResultWrapper<ApiKey, Throwable>> = _accountApiKeyFlow
}