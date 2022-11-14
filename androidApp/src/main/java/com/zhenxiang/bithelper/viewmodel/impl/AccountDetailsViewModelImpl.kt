package com.zhenxiang.bithelper.viewmodel.impl

import androidx.lifecycle.viewModelScope
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

internal class AccountDetailsViewModelImpl(
    repository: AccountDataRepository
) : AccountDetailsViewModel() {

    private val _accountApiKeyFlow = repository.apiKeyFlow.onEach {
        if (it is ResultWrapper.Success) {
            println(repository.getBalances(it.data))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ResultWrapper.Loading()
    )

    override val accountApiKeyFlow: Flow<ResultWrapper<ApiKey, Throwable>> = _accountApiKeyFlow
}