package com.zhenxiang.bithelper.shared.model

import com.zhenxiang.bithelper.shared.db.CustomEnumColumnAdapter
import com.zhenxiang.bithelper.shared.res.SharedRes
import dev.icerock.moko.resources.StringResource

enum class Exchange(
    val id: String,
    val labelRes: StringResource,
    val apiUrl: String,
    ) {
    BINANCE("binance", SharedRes.strings.binance_exchange, "https://api.binance.com/"),
    FTX("ftx", SharedRes.strings.ftx_exchange, "none");

    class ColumnAdapter : CustomEnumColumnAdapter<Exchange> {
        override val enumValues: Array<Exchange> = values()

        override fun decode(databaseValue: String): Exchange =
            enumValues.first { it.id == databaseValue }

        override fun encode(value: Exchange): String = value.id
    }
}
