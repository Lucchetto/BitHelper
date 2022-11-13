package com.zhenxiang.bithelper.shared.provider.binance

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.ktor.KtorfitFactory
import com.zhenxiang.bithelper.shared.ktor.createApiInstance
import com.zhenxiang.bithelper.shared.model.ApiResponse
import com.zhenxiang.bithelper.shared.model.Asset
import com.zhenxiang.bithelper.shared.provider.ExchangeAccountDataProvider
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class BinanceAccountDataProvider(apiKey: ApiKey) : ExchangeAccountDataProvider, KoinComponent {

    private val apiInstance by lazy {
        get<KtorfitFactory>().createApiInstance<BinanceApi>(apiKey, BinanceResponseConverter()) {
            defaultRequest {
                header("X-MBX-APIKEY", apiKey.apiKey)
            }
        }
    }

    override suspend fun getBalances(): List<Asset> = apiInstance.getUserAssets().let { res ->
        when (res) {
            is ApiResponse.Success -> res.data.map { it.toAsset() }
            // TODO: Implement error handling
            is ApiResponse.Error -> emptyList()
        }
    }
}