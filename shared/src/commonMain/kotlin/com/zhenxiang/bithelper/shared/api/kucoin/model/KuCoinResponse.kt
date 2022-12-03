package com.zhenxiang.bithelper.shared.api.kucoin.model

import kotlinx.serialization.Serializable

@Serializable
data class KuCoinResponse<T>(
    val code: String,
    val data: T
)
