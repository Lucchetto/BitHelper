package com.zhenxiang.bithelper.shared.api.binance.model

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.zhenxiang.bithelper.shared.api.model.WithdrawMethodNetworkModel
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.utils.nullIfBlank
import kotlinx.serialization.Serializable

@Serializable
data class BinanceAssetNetworkDetails(
    val name: String,
    val network: String,
    val specialTips: String?,
    val withdrawDesc: String?,
    val withdrawEnable: Boolean,
    val withdrawFee: String,
    val sameAddress: Boolean,
): WithdrawMethodNetworkModel {

    override fun toWithdrawMethod() = WithdrawMethod(
        name = name,
        exchangeInternalId = network,
        description = withdrawDesc?.nullIfBlank(),
        hints = specialTips?.nullIfBlank(),
        available = withdrawEnable,
        fee = BigDecimal.parseString(withdrawFee),
        hasMemoField = sameAddress,
    )
}
