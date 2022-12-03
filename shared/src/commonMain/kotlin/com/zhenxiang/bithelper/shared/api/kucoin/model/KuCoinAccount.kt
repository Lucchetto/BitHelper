package com.zhenxiang.bithelper.shared.api.kucoin.model

import kotlinx.serialization.Serializable

@Serializable
data class KuCoinAccount(
    val id: String,
    val type: KuCoinAccountType
)
