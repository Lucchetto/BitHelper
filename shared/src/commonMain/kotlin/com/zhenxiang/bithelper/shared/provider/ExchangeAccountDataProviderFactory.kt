package com.zhenxiang.bithelper.shared.provider

import com.zhenxiang.bithelper.shared.db.ApiKey

interface ExchangeAccountDataProviderFactory {

    fun createInstance(apiKey: ApiKey): ExchangeAccountDataProvider
}