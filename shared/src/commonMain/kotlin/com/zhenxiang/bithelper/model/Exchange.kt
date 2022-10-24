package com.zhenxiang.bithelper.model

import com.zhenxiang.bithelper.db.CustomEnumColumnAdapter

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
