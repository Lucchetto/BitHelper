package com.zhenxiang.bithelper.shared.api.binance.model

import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import com.zhenxiang.bithelper.shared.api.model.WithdrawMethodNetworkModel
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import com.zhenxiang.bithelper.shared.utils.nullIfBlank
import kotlinx.serialization.Serializable
import kotlin.math.absoluteValue

@Serializable
data class BinanceAssetNetworkDetails(
    val name: String,
    val network: String,
    val specialTips: String? = null,
    val withdrawDesc: String? = null,
    val withdrawEnable: Boolean,
    val withdrawFee: String,
    val withdrawIntegerMultiple: String,
    val withdrawMin: String,
    val sameAddress: Boolean,
): WithdrawMethodNetworkModel {

    override fun toWithdrawMethod() = WithdrawMethod(
        name = name,
        exchangeInternalId = network,
        description = withdrawDesc?.nullIfBlank(),
        hints = specialTips?.nullIfBlank(),
        available = withdrawEnable,
        // withdrawIntegerMultiple is in format 0.0001 so we can calculate the precision from
        // it's exponent (always negative, but coerce to 0 just for safety) and make it positive
        decimalPrecision = withdrawIntegerMultiple.toBigDecimal().exponent.coerceAtMost(0).absoluteValue.toInt(),
        fee = withdrawFee.toBigDecimal(),
        minAmount = withdrawMin.toBigDecimal(),
        hasMemoField = sameAddress,
    )
}
