package com.zhenxiang.bithelper.shared.provider

import com.zhenxiang.bithelper.shared.model.Asset

interface ExchangeAccountDataProvider {

    suspend fun getBalances(): List<Asset>
}