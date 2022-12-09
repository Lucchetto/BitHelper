package com.zhenxiang.bithelper.shared.api.binance.model

import com.zhenxiang.bithelper.shared.model.WithdrawRequest
import kotlinx.serialization.Serializable

@Serializable
data class BinanceWithdrawRequest(
    val coin: String,
    val address: String,
    val addressTag: String? = null,
    val amount: String,
) {

    constructor(
        withdrawRequest: WithdrawRequest
    ): this(
        coin = withdrawRequest.assetTicker,
        address = withdrawRequest.address,
        addressTag = withdrawRequest.memo,
        amount = withdrawRequest.amount.toStringExpanded()
    )
}
