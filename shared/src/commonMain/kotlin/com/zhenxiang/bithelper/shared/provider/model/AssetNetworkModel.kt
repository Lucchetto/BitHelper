package com.zhenxiang.bithelper.shared.provider.model

import com.zhenxiang.bithelper.shared.model.AssetBalance

interface AssetNetworkModel {

    fun toAsset(): AssetBalance
}
