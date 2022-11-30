package com.zhenxiang.bithelper.shared.api

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.api.binance.BinanceApiClientImpl
import com.zhenxiang.bithelper.shared.api.kucoin.KuCoinApiClientImpl

internal class ExchangeApiClientProviderImpl : ExchangeApiClientProvider {

    private var client: ExchangeApiClient? = null

    override fun getInstance(apiKey: ApiKey): ExchangeApiClient {
        val currentInstance = client
        if (currentInstance == null || currentInstance.exchange != apiKey.exchange) {
            val newInstance = buildClient(apiKey)
            client = newInstance
            return newInstance
        } else {
            currentInstance.setApiKey(apiKey)
            return currentInstance
        }
    }

    private fun buildClient(apiKey: ApiKey): ExchangeApiClient = when (apiKey.exchange) {
        Exchange.BINANCE -> BinanceApiClientImpl(apiKey)
        Exchange.FTX -> TODO("Maybe FTX should be dropped")
        Exchange.KUCOIN -> KuCoinApiClientImpl(apiKey)
    }
}