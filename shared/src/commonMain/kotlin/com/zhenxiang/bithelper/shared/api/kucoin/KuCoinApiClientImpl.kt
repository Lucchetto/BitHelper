package com.zhenxiang.bithelper.shared.api.kucoin

import com.zhenxiang.bithelper.shared.api.BaseExchangeApiClientImpl
import com.zhenxiang.bithelper.shared.api.kucoin.model.KuCoinAccountType
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.api.model.ExchangeResultWrapper
import com.zhenxiang.bithelper.shared.crypto.HmacHash
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.ktor.KtorfitFactory
import com.zhenxiang.bithelper.shared.ktor.createApiInstance
import com.zhenxiang.bithelper.shared.model.*
import com.zhenxiang.bithelper.shared.utils.toBase64String
import io.ktor.http.*
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class KuCoinApiClientImpl(apiKey: ApiKey) : BaseExchangeApiClientImpl(apiKey), KoinComponent {

    private val apiInstance by lazy {
        get<KtorfitFactory>().createApiInstance<KuCoinApi>(apiKey.exchange, KuCoinResponseConverter()) {

            val timestamp = Clock.System.now().toEpochMilliseconds().toString()

            headers[TIMESTAMP_HEADER] = timestamp
            headers[API_KEY_HEADER] = currentApiKey.apiKey
            if (apiKey.password != null && apiKey.secretKey != null) {
                val signature = "$timestamp${method.value}${url.build().encodedPathAndQuery}"
                headers[SIGNATURE_HEADER] = HmacHash.sha256(signature, apiKey.secretKey).toBase64String()
                headers[PASSPHRASE_HEADER] = HmacHash.sha256(apiKey.password, apiKey.secretKey).toBase64String()
            }
            headers[API_KEY_VERSION_HEADER] = API_KEY_VERSION_VALUE
        }
    }

    override val exchange: Exchange
        get() = Exchange.KUCOIN

    override suspend fun getBalances(): ExchangeResultWrapper<List<AssetBalance>> {
        println(apiInstance.getAccounts(KuCoinAccountType.MAIN))
        return ResultWrapper.Error(ExchangeApiError.UnknownError)
    }

    override suspend fun getAssetWithdrawMethods(assetTicker: String): ExchangeResultWrapper<List<WithdrawMethod>> {
        TODO("Not yet implemented")
    }

    companion object {

        private const val SIGNATURE_HEADER = "KC-API-SIGN"
        private const val TIMESTAMP_HEADER = "KC-API-TIMESTAMP"
        private const val API_KEY_HEADER = "KC-API-KEY"
        private const val PASSPHRASE_HEADER = "KC-API-PASSPHRASE"
        private const val API_KEY_VERSION_HEADER = "KC-API-KEY-VERSION"
        private const val API_KEY_VERSION_VALUE = "2"
    }
}