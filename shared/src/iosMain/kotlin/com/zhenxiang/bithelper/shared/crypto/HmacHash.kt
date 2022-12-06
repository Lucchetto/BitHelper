package com.zhenxiang.bithelper.shared.crypto

import kotlinx.cinterop.*
import platform.CoreCrypto.CCHmac
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH
import platform.CoreCrypto.kCCHmacAlgSHA256
import platform.posix.strlen

actual object HmacHash {

    actual fun sha256(message: String, key: String): ByteArray = memScoped {
        val hashedPtrArray: CArrayPointer<ByteVar> = allocArray(CC_SHA256_DIGEST_LENGTH)
        CCHmac(
            kCCHmacAlgSHA256,
            key.cstr.getPointer(this),
            strlen(key),
            message.cstr.getPointer(this),
            strlen(message),
            hashedPtrArray
        )
        (0 until CC_SHA256_DIGEST_LENGTH).map { hashedPtrArray[it] }.toByteArray()
    }
}