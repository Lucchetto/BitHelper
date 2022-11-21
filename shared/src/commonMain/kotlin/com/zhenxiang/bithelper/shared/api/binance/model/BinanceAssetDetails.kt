package com.zhenxiang.bithelper.shared.api.binance.model

@kotlinx.serialization.Serializable
data class BinanceAssetDetails(
    val coin: String,
    val networkList: List<BinanceAssetNetworkDetails>
)
