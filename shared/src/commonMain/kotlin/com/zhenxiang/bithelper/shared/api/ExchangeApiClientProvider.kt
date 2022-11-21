package com.zhenxiang.bithelper.shared.api

import com.zhenxiang.bithelper.shared.db.ApiKey

interface ExchangeApiClientProvider {

    /**
     * Create new or reuse existing instance of [ExchangeApiClient] for given [ApiKey]
     */
    fun getInstance(apiKey: ApiKey): ExchangeApiClient
}