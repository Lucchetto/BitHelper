package com.zhenxiang.bithelper.android.flow

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

fun <T : Any> Flow<Query<T>>.mapToListOnIO() = mapToList(Dispatchers.IO)