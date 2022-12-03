package com.zhenxiang.bithelper.shared.api.kucoin.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class KuCoinAccountType(val id: String) {
    @SerialName("main") MAIN("main"),
    @SerialName("trade") TRADE("trade"),
    @SerialName("margin") MARGIN("margin");

    override fun toString(): String = id
}
