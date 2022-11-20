package com.zhenxiang.bithelper.shared.provider.binance

import com.zhenxiang.bithelper.shared.model.ExchangeApiResponse
import com.zhenxiang.bithelper.shared.provider.binance.model.BinanceAssetBalance
import de.jensklingenberg.ktorfit.http.POST
import io.ktor.client.request.*

interface BinanceApi {

    @POST("sapi/v3/asset/getUserAsset")
    suspend fun getUserAssets(): ExchangeApiResponse<List<BinanceAssetBalance>>
}
