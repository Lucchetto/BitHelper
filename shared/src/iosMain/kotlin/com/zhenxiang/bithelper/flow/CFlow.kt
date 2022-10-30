package com.zhenxiang.bithelper.flow

import com.squareup.sqldelight.db.Closeable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.wrap(): CFlow<T> = CFlow(this)

class CFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(NSLooperDispatcher + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}
