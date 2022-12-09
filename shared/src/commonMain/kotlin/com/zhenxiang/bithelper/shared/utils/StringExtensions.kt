package com.zhenxiang.bithelper.shared.utils

fun String.nullIfBlank(): String? = ifBlank { null }

fun String.removeSpacesAndNewLines(): String = replace("\\s".toRegex(), "")
