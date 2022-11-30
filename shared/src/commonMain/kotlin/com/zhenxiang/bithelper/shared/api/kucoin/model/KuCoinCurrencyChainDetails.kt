package com.zhenxiang.bithelper.shared.api.kucoin.model

import com.zhenxiang.bithelper.shared.api.model.WithdrawMethodNetworkModel
import com.zhenxiang.bithelper.shared.model.WithdrawMethod
import kotlinx.serialization.Serializable

@Serializable
data class KuCoinCurrencyChainDetails(
    val chainName: String,
    val chain: String,
    val isWithdrawEnabled: Boolean
): WithdrawMethodNetworkModel {

    override fun toWithdrawMethod() = WithdrawMethod(
        name = chainName,
        exchangeInternalId = chain,
        description = null,
        hints = null,
        available = isWithdrawEnabled,
        fee = null,
        hasMemoField = null
    )
}
