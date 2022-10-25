package com.zhenxiang.bithelper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform