package com.zhenxiang.bithelper.viewmodel.impl

import androidx.lifecycle.viewModelScope
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import com.zhenxiang.bithelper.viewmodel.WithdrawSheetViewModel
import kotlinx.coroutines.flow.*

internal class WithdrawSheetViewModelImpl(
    assetTicker: String,
    private val repository: AccountDataRepository,
): WithdrawSheetViewModel(assetTicker) {

    private val _withdrawMethodsFlow = MutableStateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>(ResultWrapper.Loading())
    override val withdrawMethodsFlow: StateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>> = _withdrawMethodsFlow

    private val accountApiKeyFlow = repository.apiKeyFlow.onEach {
        if (it is ResultWrapper.Success) {
            _withdrawMethodsFlow.emit(repository.getAssetWithdrawMethods(it.data, assetTicker))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        ResultWrapper.Loading()
    )
}