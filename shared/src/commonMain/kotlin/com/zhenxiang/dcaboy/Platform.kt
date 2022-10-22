package com.zhenxiang.dcaboy

import org.koin.dsl.module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform