package com.zhenxiang.bithelper.shared.provider.binance

import com.zhenxiang.bithelper.shared.provider.binance.model.BinanceAsset
import de.jensklingenberg.ktorfit.http.POST

internal interface BinanceApi {

    @POST("/sapi/v3/asset/getUserAsset")
    suspend fun getUserAssets(): List<BinanceAsset>
}