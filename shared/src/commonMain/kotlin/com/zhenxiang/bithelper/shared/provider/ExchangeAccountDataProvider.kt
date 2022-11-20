package com.zhenxiang.bithelper.shared.provider

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.provider.model.ExchangeResultWrapper

interface ExchangeAccountDataProvider {

    val currentApiKey: ApiKey

    val exchange: Exchange

    fun setApiKey(apiKey: ApiKey)

    suspend fun getBalances(): ExchangeResultWrapper<List<AssetBalance>>
}