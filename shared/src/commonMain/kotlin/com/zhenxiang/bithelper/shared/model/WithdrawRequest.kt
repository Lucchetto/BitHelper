package com.zhenxiang.bithelper.shared.model

import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class WithdrawRequest(
    val assetTicker: String,
    val address: String,
    val memo: String? = null,
    val amount: BigDecimal,
)
