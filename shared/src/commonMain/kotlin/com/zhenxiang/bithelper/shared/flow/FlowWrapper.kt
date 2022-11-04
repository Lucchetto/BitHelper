package com.zhenxiang.bithelper.shared.flow

import kotlinx.coroutines.flow.Flow

/**
 * Wrapper for [Flow] to preserve type correctly on iOS
 */
expect class FlowWrapper<T>(source: Flow<T>) : Flow<T>
