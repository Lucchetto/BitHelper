package com.zhenxiang.bithelper.ios.flow

import com.squareup.sqldelight.db.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.wrap(): FlowWrapper<T> = FlowWrapper(this)

class FlowWrapper<T>(source: Flow<T>) : Flow<T> by source {

    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Default + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}
