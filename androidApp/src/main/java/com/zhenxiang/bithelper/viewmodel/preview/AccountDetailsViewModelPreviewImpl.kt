package com.zhenxiang.bithelper.viewmodel.preview

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.provider.model.ExchangeApiError
import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import kotlinx.coroutines.flow.*

class AccountDetailsViewModelPreviewImpl: AccountDetailsViewModel() {

    override val accountBalancesFlow: StateFlow<ResultWrapper<List<AssetBalance>, ExchangeApiError>>
        get() = MutableStateFlow(
            ResultWrapper.Success(
                listOf(
                    AssetBalance("ETH", 69.420)
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