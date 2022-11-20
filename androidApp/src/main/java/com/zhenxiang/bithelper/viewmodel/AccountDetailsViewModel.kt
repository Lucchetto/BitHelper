package com.zhenxiang.bithelper.viewmodel

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.provider.model.ExchangeApiError
import kotlinx.coroutines.flow.StateFlow

abstract class AccountDetailsViewModel: ViewModel() {

    abstract val accountBalancesFlow: StateFlow<ResultWrapper<List<AssetBalance>, ExchangeApiError>>

    abstract val accountApiKeyFlow: StateFlow<ResultWrapper<ApiKey, Throwable>>
}
