package com.zhenxiang.bithelper.viewmodel.preview

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AccountDetailsViewModelPreviewImpl: AccountDetailsViewModel() {
    override val accountApiKeyFlow: Flow<ResultWrapper<ApiKey, Throwable>>
        get() = flowOf(
            ResultWrapper.Success(
                ApiKey(
                    id = 0,
                    apiKey = "0000000000000000",
                    exchange = Exchange.BINANCE,
                    label = "Sample API key",
                    secretKey = null,
                    creationTimestamp = 0,
                    readOnly = null,
                )
            )
        )
}