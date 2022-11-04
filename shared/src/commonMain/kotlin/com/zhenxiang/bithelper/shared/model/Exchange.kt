package com.zhenxiang.bithelper.shared.model

import com.zhenxiang.bithelper.shared.db.CustomEnumColumnAdapter

enum class Exchange(val id: String) {
    BINANCE("binance"),
    FTX("ftx");

    class ColumnAdapter : CustomEnumColumnAdapter<Exchange> {
        override val enumValues: Array<Exchange> = values()

        override fun decode(databaseValue: String): Exchange =
            enumValues.first { it.id == databaseValue }

        override fun encode(value: Exchange): String = value.id
    }
}
