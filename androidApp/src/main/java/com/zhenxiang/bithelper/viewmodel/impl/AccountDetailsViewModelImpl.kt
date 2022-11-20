package com.zhenxiang.bithelper.viewmodel.impl

import androidx.lifecycle.viewModelScope
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.Asset
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.provider.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import kotlinx.coroutines.flow.*

internal class AccountDetailsViewModelImpl(
    repository: AccountDataRepository
) : AccountDetailsViewModel() {

    private val _accountBalances = MutableStateFlow<ResultWrapper<List<Asset>, ExchangeApiError>>(ResultWrapper.Loading())
    override val accountBalancesFlow: StateFlow<ResultWrapper<List<Asset>, ExchangeApiError>> = _accountBalances

    private val _accountApiKeyFlow = repository.apiKeyFlow.onEach {
        if (it is ResultWrapper.Success) {
            _accountBalances.emit(repository.getBalances(it.data))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ResultWrapper.Loading()
    )

    override val accountApiKeyFlow: StateFlow<ResultWrapper<ApiKey, Throwable>> = _accountApiKeyFlow
}