package com.zhenxiang.bithelper.shared.utils

fun String.nullIfBlank(): String? = ifBlank { null }
