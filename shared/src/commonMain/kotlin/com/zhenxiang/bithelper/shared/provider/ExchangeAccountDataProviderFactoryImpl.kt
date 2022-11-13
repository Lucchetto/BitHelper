package com.zhenxiang.bithelper.shared.provider

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.provider.binance.BinanceAccountDataProvider

internal class ExchangeAccountDataProviderFactoryImpl : ExchangeAccountDataProviderFactory {

    override fun createInstance(apiKey: ApiKey) = when (apiKey.exchange) {
        Exchange.BINANCE -> BinanceAccountDataProvider(apiKey)
        Exchange.FTX -> TODO("Maybe FTX should be dropped")
    }
}