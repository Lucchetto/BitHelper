package com.zhenxiang.bithelper.shared.provider.binance.model

import com.zhenxiang.bithelper.shared.model.Asset
import com.zhenxiang.bithelper.shared.provider.model.AssetNetworkModel
import kotlinx.serialization.Serializable

@Serializable
data class BinanceAsset(
    val asset: String,
    val free: String
): AssetNetworkModel {

    override fun toAsset(): Asset = Asset(
        asset,
        free.toDouble(),
    )
}
