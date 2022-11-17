package com.zhenxiang.bithelper.shared.crypto

expect class HmacHash {

    companion object {
        /**
         * @param message the message to be hashed
         * @param key secret key used to hash the message
         *
         * @return the encrypted message as a [ByteArray]
         */
        fun sha256(message: String, key: String): ByteArray
    }
}
