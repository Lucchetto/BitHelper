package com.zhenxiang.bithelper.shared.api.kucoin

import com.zhenxiang.bithelper.shared.api.BaseExchangeApiClientImpl
import com.zhenxiang.bithelper.shared.api.binance.BinanceApi
import com.zhenxiang.bithelper.shared.api.binance.BinanceApiClientImpl
import com.zhenxiang.bithelper.shared.api.binance.BinanceResponseConverter
import com.zhenxiang.bithelper.shared.crypto.HmacHash
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.ktor.KtorfitFactory
import com.zhenxiang.bithelper.shared.ktor.createApiInstance
import com.zhenxiang.bithelper.shared.model.Exchange
import com.zhenxiang.bithelper.shared.utils.toBase64String
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class KuCoinApiClientImpl(apiKey: ApiKey) : BaseExchangeApiClientImpl(apiKey), KoinComponent {

    private val apiInstance by lazy {
        get<KtorfitFactory>().createApiInstance<BinanceApi>(apiKey.exchange, BinanceResponseConverter()) {

            headers[TIMESTAMP_HEADER] = Clock.System.now().toEpochMilliseconds().toString()
            headers[API_KEY_HEADER] = currentApiKey.apiKey
            if (apiKey.password != null && apiKey.secretKey != null) {
                headers[PASSPHRASE_HEADER] = HmacHash.sha256(apiKey.secretKey, apiKey.password).toBase64String()
            }
        }
    }

    override val exchange: Exchange
        get() = Exchange.KUCOIN

    companion object {

        private const val SIGNATURE_HEADER = "KC-API-SIGN"
        private const val TIMESTAMP_HEADER = "KC-API-TIMESTAMP"
        private const val API_KEY_HEADER = "KC-API-KEY"
        private const val PASSPHRASE_HEADER = "KC-API-PASSPHRASE"
        private const val API_KEY_VERSION_HEADER = "KC-API-KEY-VERSION"
    }
}