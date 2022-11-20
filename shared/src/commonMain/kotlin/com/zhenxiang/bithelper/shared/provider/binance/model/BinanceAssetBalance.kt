package com.zhenxiang.bithelper.shared.provider.binance.model

import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.provider.model.AssetNetworkModel
import kotlinx.serialization.Serializable

@Serializable
data class BinanceAssetBalance(
    val asset: String,
    val free: String
): AssetNetworkModel {

    override fun toAsset(): AssetBalance = AssetBalance(
        asset,
        free.toDouble(),
    )
}
