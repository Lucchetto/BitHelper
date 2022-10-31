package com.zhenxiang.bithelper.flow

import kotlinx.coroutines.flow.Flow

/**
 * Wrapper for [Flow] to preserve type correctly on iOS
 */
expect class FlowWrapper<T>(source: Flow<T>) : Flow<T>
