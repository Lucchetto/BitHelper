package com.zhenxiang.bithelper.shared.model

data class WithdrawMethod(
    val name: String,
    val exchangeInternalId: String,
    val description: String,
    val available: Boolean,
    val fee: Double?,
    val memoRequired: Boolean?,
)
