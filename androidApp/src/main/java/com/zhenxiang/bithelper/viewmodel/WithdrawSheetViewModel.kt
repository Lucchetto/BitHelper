package com.zhenxiang.bithelper.viewmodel

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import kotlinx.coroutines.flow.StateFlow

abstract class WithdrawSheetViewModel(
    private val assetTicker: String,
): ViewModel() {

    abstract val withdrawMethodsFlow: StateFlow<ResultWrapper<List<WithdrawMethod>, ExchangeApiError>>
}
