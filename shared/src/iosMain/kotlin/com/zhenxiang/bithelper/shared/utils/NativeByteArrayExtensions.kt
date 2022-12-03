package com.zhenxiang.bithelper.shared.utils

import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.memScoped
import platform.Foundation.NSData
import platform.Foundation.base64Encoding
import platform.Foundation.create

actual fun ByteArray.toBase64String(): String = memScoped {
    NSData.create(
        bytes = allocArrayOf(this@toBase64String),
        length = this@toBase64String.size.toULong()
    ).base64Encoding()
}
