package com.zhenxiang.bithelper.shared.api.binance

import com.zhenxiang.bithelper.shared.model.ExchangeApiResponse
import com.zhenxiang.bithelper.shared.api.binance.model.BinanceAssetBalance
import com.zhenxiang.bithelper.shared.api.binance.model.BinanceAssetDetails
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.request.*

interface BinanceApi {

    @POST("sapi/v3/asset/getUserAsset")
    suspend fun getUserAssets(@Query("asset") assetTicker: String? = null): ExchangeApiResponse<List<BinanceAssetBalance>>

    @GET("sapi/v1/capital/config/getall")
    suspend fun getAllAssetsDetails(): ExchangeApiResponse<List<BinanceAssetDetails>>
}
