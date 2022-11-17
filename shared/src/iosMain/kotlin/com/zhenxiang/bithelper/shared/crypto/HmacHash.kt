package com.zhenxiang.bithelper.shared.crypto

import kotlinx.cinterop.*
import platform.CoreCrypto.CCHmac
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH
import platform.CoreCrypto.kCCHmacAlgSHA256
import platform.posix.strlen

actual class HmacHash {

    actual companion object {

        actual fun sha256(message: String, key: String): ByteArray {
            memScoped {
                val targetLen = CC_SHA256_DIGEST_LENGTH
                val hashedPtrArray: CArrayPointer<ByteVar> = nativeHeap.allocArray(targetLen)
                CCHmac(
                    kCCHmacAlgSHA256,
                    key.cstr.getPointer(this),
                    strlen(key),
                    message.cstr.getPointer(this),
                    strlen(message),
                    hashedPtrArray
                )
                val result = (0 until targetLen).map { hashedPtrArray[it] }.toByteArray()
                nativeHeap.free(hashedPtrArray)
                return result
            }
        }
    }
}