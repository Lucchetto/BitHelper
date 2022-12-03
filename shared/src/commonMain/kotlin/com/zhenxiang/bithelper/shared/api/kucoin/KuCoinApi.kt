package com.zhenxiang.bithelper.shared.api.kucoin

import com.zhenxiang.bithelper.shared.api.binance.model.BinanceAssetBalance
import com.zhenxiang.bithelper.shared.api.binance.model.BinanceAssetDetails
import com.zhenxiang.bithelper.shared.api.kucoin.model.KuCoinAccount
import com.zhenxiang.bithelper.shared.api.kucoin.model.KuCoinAccountType
import com.zhenxiang.bithelper.shared.api.kucoin.model.KuCoinCurrencyDetails
import com.zhenxiang.bithelper.shared.api.kucoin.model.KuCoinResponse
import com.zhenxiang.bithelper.shared.model.ExchangeApiResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface KuCoinApi {

    @GET("api/v1/accounts")
    suspend fun getAccounts(@Query("type") type: KuCoinAccountType? = null): ExchangeApiResponse<KuCoinResponse<List<KuCoinAccount>>>

    @POST("sapi/v3/asset/getUserAsset")
    suspend fun getUserAssets(): ExchangeApiResponse<List<BinanceAssetBalance>>

    @GET("/api/v2/currencies/{currency}")
    suspend fun getAllAssetsDetails(@Path("currency") currency: String): ExchangeApiResponse<KuCoinCurrencyDetails>
}