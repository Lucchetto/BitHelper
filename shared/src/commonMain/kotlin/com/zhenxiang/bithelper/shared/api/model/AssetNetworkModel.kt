package com.zhenxiang.bithelper.shared.api.model

import com.zhenxiang.bithelper.shared.model.AssetBalance

interface AssetNetworkModel {

    fun toAsset(): AssetBalance
}
