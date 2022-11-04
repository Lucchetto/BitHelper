package com.zhenxiang.bithelper.shared.flow

import kotlinx.coroutines.flow.Flow

actual class FlowWrapper<T> actual constructor(private val source: Flow<T>) : Flow<T> by source
