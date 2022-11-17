package com.zhenxiang.bithelper.shared.provider.binance

import com.zhenxiang.bithelper.shared.crypto.HmacHash
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.ktor.KtorfitFactory
import com.zhenxiang.bithelper.shared.ktor.createApiInstance
import com.zhenxiang.bithelper.shared.model.Asset
import com.zhenxiang.bithelper.shared.model.mapToResult
import com.zhenxiang.bithelper.shared.provider.ExchangeAccountDataProvider
import com.zhenxiang.bithelper.shared.provider.model.ExchangeResultWrapper
import com.zhenxiang.bithelper.shared.utils.toHex
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class BinanceAccountDataProvider(private val apiKey: ApiKey) : ExchangeAccountDataProvider, KoinComponent {

    private val apiInstance by lazy {
        get<KtorfitFactory>().createApiInstance<BinanceApi>(apiKey, BinanceResponseConverter()) {
            defaultRequest {
                header("X-MBX-APIKEY", apiKey.apiKey)
            }
        }
    }

    override suspend fun getBalances(): ExchangeResultWrapper<List<Asset>> = apiInstance.getUserAssets {
        url.apply {
            parameters["timestamp"] = (Clock.System.now().toEpochMilliseconds() - 1000).toString()
            val queryString = parameters.entries().joinToString(separator = "&") { "${it.key}=${it.value.first()}" }
            apiKey.secretKey?.let {
                parameters["signature"] = HmacHash.sha256(queryString, it).toHex()
            }
        }
    }.mapToResult {
        it.map { item -> item.toAsset() }
    }
}