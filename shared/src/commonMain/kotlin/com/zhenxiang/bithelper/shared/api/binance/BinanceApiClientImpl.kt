package com.zhenxiang.bithelper.shared.api.binance

import com.zhenxiang.bithelper.shared.crypto.HmacHash
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.ktor.KtorfitFactory
import com.zhenxiang.bithelper.shared.ktor.createApiInstance
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.model.mapToResult
import com.zhenxiang.bithelper.shared.api.BaseExchangeApiClientImpl
import com.zhenxiang.bithelper.shared.api.model.ExchangeResultWrapper
import com.zhenxiang.bithelper.shared.utils.toHex
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class BinanceApiClientImpl(apiKey: ApiKey) : BaseExchangeApiClientImpl(apiKey), KoinComponent {

    private val apiInstance by lazy {
        get<KtorfitFactory>().createApiInstance<BinanceApi>(apiKey.exchange, BinanceResponseConverter()) {
            headers[API_KEY_HEADER] = currentApiKey.apiKey
            url.apply {
                // TODO: implement server time sync and remove timestamp offset hack
                parameters[TIMESTAMP_QUERY_PARAM] = (Clock.System.now().toEpochMilliseconds() - 1000).toString()

                // Generate request signature as defined in documentation
                // https://binance-docs.github.io/apidocs/spot/en/#signed-trade-user_data-and-margin-endpoint-security
                val queryString = parameters.entries().joinToString(separator = "&") { "${it.key}=${it.value.first()}" }
                currentApiKey.secretKey?.let {
                    parameters[SIGNATURE_QUERY_PARAM] = HmacHash.sha256(queryString, it).toHex()
                }
            }
        }
    }

    override val exchange: Exchange
        get() = Exchange.BINANCE

    override suspend fun getBalances(): ExchangeResultWrapper<List<AssetBalance>> = apiInstance.getUserAssets().mapToResult {
        it.map { item -> item.toAsset() }
    }

    companion object {
        private const val API_KEY_HEADER = "X-MBX-APIKEY"
        private const val TIMESTAMP_QUERY_PARAM = "timestamp"
        private const val SIGNATURE_QUERY_PARAM = "signature"
    }
}