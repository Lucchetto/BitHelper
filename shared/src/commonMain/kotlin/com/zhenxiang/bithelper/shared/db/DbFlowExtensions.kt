package com.zhenxiang.bithelper.shared.db

import com.squareup.sqldelight.Query
import kotlinx.coroutines.flow.Flow

/**
 * Util extension to use IO dispatcher when possible to execute the list [Query]
 */
expect fun <T : Any> Flow<Query<T>>.mapToListOnIO(): Flow<List<T>>
