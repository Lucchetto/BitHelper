package com.zhenxiang.bithelper.viewmodel.preview

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.Asset
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.provider.model.ExchangeApiError
import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import kotlinx.coroutines.flow.*

class AccountDetailsViewModelPreviewImpl: AccountDetailsViewModel() {

    override val accountBalances: StateFlow<ResultWrapper<List<Asset>, ExchangeApiError>>
        get() = MutableStateFlow(
            ResultWrapper.Success(
                listOf(
                    Asset("ETH", 69.420)
                )
            )
        )

    override val accountApiKeyFlow: StateFlow<ResultWrapper<ApiKey, Throwable>>
        get() = MutableStateFlow(
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