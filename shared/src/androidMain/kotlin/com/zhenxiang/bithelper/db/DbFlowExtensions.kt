package com.zhenxiang.bithelper.db

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

actual fun <T : Any> Flow<Query<T>>.mapToListOnIO(): Flow<List<T>> = mapToList(Dispatchers.IO)
