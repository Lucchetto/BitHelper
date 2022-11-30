package com.zhenxiang.bithelper.shared.api.kucoin.model

import kotlinx.serialization.Serializable

@Serializable
data class KuCoinCurrencyDetails(
    val currency: String,
    val chains: List<KuCoinCurrencyChainDetails>
)
