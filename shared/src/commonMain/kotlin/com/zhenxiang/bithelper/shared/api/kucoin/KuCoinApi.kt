package com.zhenxiang.bithelper.shared.api.kucoin

import com.zhenxiang.bithelper.shared.api.binance.model.BinanceAssetBalance
import com.zhenxiang.bithelper.shared.api.binance.model.BinanceAssetDetails
import com.zhenxiang.bithelper.shared.api.kucoin.model.KuCoinCurrencyDetails
import com.zhenxiang.bithelper.shared.model.ExchangeApiResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path

interface KuCoinApi {

    @POST("sapi/v3/asset/getUserAsset")
    suspend fun getUserAssets(): ExchangeApiResponse<List<BinanceAssetBalance>>

    @GET("/api/v2/currencies/{currency}")
    suspend fun getAllAssetsDetails(@Path("currency") currency: String): ExchangeApiResponse<KuCoinCurrencyDetails>
}