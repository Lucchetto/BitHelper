package com.zhenxiang.dcaboy

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform