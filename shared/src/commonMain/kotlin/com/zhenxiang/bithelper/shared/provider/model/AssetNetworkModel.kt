package com.zhenxiang.bithelper.shared.provider.model

import com.zhenxiang.bithelper.shared.model.Asset

interface AssetNetworkModel {

    fun toAsset(): Asset
}
