package com.zhenxiang.bithelper.viewmodel.preview

import androidx.lifecycle.SavedStateHandle
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.viewmodel.WithdrawPageViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class WithdrawPageViewModelPreview(
    override val assetTicker: String,
    mockWithdrawMethods: List<WithdrawMethod>
): WithdrawPageViewModel(SavedStateHandle()) {

    override val withdrawMethodsFlow: StateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>> =
        MutableStateFlow(ResultWrapper.Success(mockWithdrawMethods))

    override fun withdraw() = Unit
}