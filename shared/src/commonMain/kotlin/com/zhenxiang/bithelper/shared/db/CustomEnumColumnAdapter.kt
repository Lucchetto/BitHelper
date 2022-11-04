package com.zhenxiang.bithelper.shared.db

import com.squareup.sqldelight.ColumnAdapter

interface CustomEnumColumnAdapter<T: Enum<T>> : ColumnAdapter<T, String> {

    val enumValues: Array<T>
}