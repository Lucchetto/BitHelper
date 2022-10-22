package com.zhenxiang.dcaboy

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Greeting : KoinComponent {
    private val platform: Platform by inject()

    fun greeting(): String {
        return "Hello, ${platform.name}!"
    }
}