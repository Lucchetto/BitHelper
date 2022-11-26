package com.zhenxiang.bithelper.viewmodel.impl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.zhenxiang.bithelper.form.CustomValidators
import com.zhenxiang.bithelper.form.TypedChoiceState
import com.zhenxiang.bithelper.form.ValidationMessages
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import com.zhenxiang.bithelper.viewmodel.WithdrawPageViewModel
import kotlinx.coroutines.flow.*

internal class WithdrawPageViewModelImpl(
    savedStateHandle: SavedStateHandle,
    private val repository: AccountDataRepository,
): WithdrawPageViewModel(savedStateHandle) {

    private val apiKeyId = savedStateHandle.get<Long>(API_KEY_ID_ARG)!!
    override val assetTicker = savedStateHandle.get<String>(ASSET_TICKER_ARG)!!

    private val _withdrawMethodsFlow = MutableStateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>(ResultWrapper.Loading())
    override val withdrawMethodsFlow: StateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>> = _withdrawMethodsFlow

    private val accountApiKeyFlow = repository.getApiKeyFlow(apiKeyId).onEach {
        if (it is ResultWrapper.Success) {
            _withdrawMethodsFlow.emit(repository.getAssetWithdrawMethods(it.data, assetTicker))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        ResultWrapper.Loading()
    )

    override fun withdraw() {
    }
}