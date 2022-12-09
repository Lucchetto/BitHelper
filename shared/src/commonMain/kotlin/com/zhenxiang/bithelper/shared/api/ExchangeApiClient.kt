package com.zhenxiang.bithelper.shared.api

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.api.model.ExchangeResultWrapper
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.model.WithdrawRequest

interface ExchangeApiClient {

    val currentApiKey: ApiKey

    val exchange: Exchange

    fun setApiKey(apiKey: ApiKey)

    suspend fun getBalances(): ExchangeResultWrapper<List<AssetBalance>>

    suspend fun getAssetBalance(assetTicker: String): ExchangeResultWrapper<AssetBalance>

    suspend fun getAssetWithdrawMethods(assetTicker: String): ExchangeResultWrapper<List<WithdrawMethod>>

    suspend fun withdraw(withdrawRequest: WithdrawRequest): ExchangeResultWrapper<Unit>
}