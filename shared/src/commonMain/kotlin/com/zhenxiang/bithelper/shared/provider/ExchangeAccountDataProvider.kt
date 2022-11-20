package com.zhenxiang.bithelper.shared.provider

import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.provider.model.ExchangeResultWrapper

interface ExchangeAccountDataProvider {

    suspend fun getBalances(): ExchangeResultWrapper<List<AssetBalance>>
}