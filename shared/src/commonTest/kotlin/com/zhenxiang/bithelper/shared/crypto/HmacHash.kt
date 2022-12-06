package com.zhenxiang.bithelper.shared.crypto

import com.zhenxiang.bithelper.shared.utils.toHex
import kotlin.test.Test
import kotlin.test.assertEquals

class HmacHashTest {

    @Test
    fun `given message and key Then returns hashed message`() {
        assertEquals(
            "8b5f48702995c1598c573db1e21866a9b825d4a794d169d7060a03605796360b",
            HmacHash.sha256("message", "secret").toHex()
        )
    }
}
