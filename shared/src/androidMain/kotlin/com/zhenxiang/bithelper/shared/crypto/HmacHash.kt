package com.zhenxiang.bithelper.shared.crypto

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

actual class HmacHash {
    
    actual companion object {
        private const val HMAC_SHA256_ALGORITHM_NAME = "HmacSHA256"

        actual fun sha256(message: String, key: String): ByteArray {
            val mac = Mac.getInstance(HMAC_SHA256_ALGORITHM_NAME)
            mac.init(SecretKeySpec(key.toByteArray(), HMAC_SHA256_ALGORITHM_NAME))

            return mac.doFinal(message.toByteArray())
        }
    }
}
