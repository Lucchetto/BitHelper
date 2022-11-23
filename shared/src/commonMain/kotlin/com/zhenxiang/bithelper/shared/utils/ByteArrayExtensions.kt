package com.zhenxiang.bithelper.shared.utils

private val HEX_CHARS = "0123456789abcdef".toCharArray()

fun ByteArray.toHex() : String = joinToString(separator = "") {
    val octet = it.toInt()
    val firstIndex = (octet and 0xF0).ushr(4)
    val secondIndex = octet and 0x0F

    "${HEX_CHARS[firstIndex]}${HEX_CHARS[secondIndex]}"
}
