package com.zhenxiang.bithelper.shared.model

data class AssetBalance(
    val ticker: String,
    val availableBalance: Double,
) {

    companion object {
        const val PLACEHOLDER_TICKER = "WBTC"
        const val PLACEHOLDER_BALANCE = "69.000000"
        const val PLACEHOLDER_BALANCE_WITH_TICKER = "$PLACEHOLDER_BALANCE $PLACEHOLDER_TICKER"
    }
}
