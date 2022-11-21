package com.zhenxiang.bithelper.shared.api

import com.zhenxiang.bithelper.shared.db.ApiKey

internal abstract class BaseExchangeApiClientImpl(apiKey: ApiKey) : ExchangeApiClient {

    private var _apiKey: ApiKey

    init {
        validateApiKey(apiKey)
        _apiKey = apiKey
    }

    final override val currentApiKey: ApiKey
        get() = _apiKey

    final override fun setApiKey(apiKey: ApiKey) {
        validateApiKey(apiKey)
        _apiKey = apiKey
    }

    private fun validateApiKey(apiKey: ApiKey) {
        if (apiKey.exchange != exchange) {
            throw IllegalStateException("new apiKey's exchange ${apiKey.exchange} doesn't match exchange $exchange required by provider")
        }
    }
}