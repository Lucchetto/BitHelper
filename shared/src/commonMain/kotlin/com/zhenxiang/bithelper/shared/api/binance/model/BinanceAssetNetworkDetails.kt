package com.zhenxiang.bithelper.shared.api.binance.model

import com.zhenxiang.bithelper.shared.api.model.WithdrawMethodNetworkModel
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import kotlinx.serialization.Serializable

@Serializable
data class BinanceAssetNetworkDetails(
    val name: String,
    val network: String,
    val withdrawDesc: String,
    val withdrawEnable: Boolean,
    val withdrawFee: String,
    val sameAddress: Boolean,
): WithdrawMethodNetworkModel {

    override fun toWithdrawMethod() = WithdrawMethod(
        name = name,
        exchangeInternalId = network,
        description = withdrawDesc,
        available = withdrawEnable,
        fee = withdrawFee.toDouble(),
        memoRequired = sameAddress,
    )
}
