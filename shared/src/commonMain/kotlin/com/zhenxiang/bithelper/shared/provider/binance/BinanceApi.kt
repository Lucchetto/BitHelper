package com.zhenxiang.bithelper.shared.provider.binance

import com.zhenxiang.bithelper.shared.model.ExchangeApiResponse
import com.zhenxiang.bithelper.shared.provider.binance.model.BinanceAsset
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.ReqBuilder
import io.ktor.client.request.*

interface BinanceApi {

    @POST("sapi/v3/asset/getUserAsset")
    suspend fun getUserAssets(
        @ReqBuilder ext: HttpRequestBuilder.() -> Unit
    ): ExchangeApiResponse<List<BinanceAsset>>
}
