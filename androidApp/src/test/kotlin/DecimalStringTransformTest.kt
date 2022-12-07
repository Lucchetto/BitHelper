package com.zhenxiang.bithelper.form

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DecimalStringTransformTest {

    @Test(expected = IllegalArgumentException::class)
    fun `given negative precision Then IllegalArgumentException is thrown`() {
        DecimalStringTransform.transform("0.5", -10)
    }

    @Test
    fun `when transform invoked on non decimal string Then returns null`() {
        assertNull(DecimalStringTransform.transform("ashdjvna", 7))
    }

    @Test
    fun `given precision zero When transform invoked on decimal string Then returns integer part`() {
        assertEquals("69", DecimalStringTransform.transform("69.420", 0))
    }

    @Test
    fun `when transform invoked on integer string Then returns same string`() {
        val integer1 = "25023"
        val integer2 = "25023."

        assertEquals(integer1, DecimalStringTransform.transform(integer1, 8))
        assertEquals(integer2, DecimalStringTransform.transform(integer2, 7))
    }

    @Test
    fun `when transform invoked on decimal string with spaces Then returns same trimmed decimal string`() {
            assertEquals("25023.100", DecimalStringTransform.transform(" 25023.100 ", 7))
    }

    @Test
    fun `when transform invoked on decimal string with exceeding precision Then returns decimal string with trimmed precision`() {
        assertEquals("25023.1000001", DecimalStringTransform.transform("25023.100000105", 7))
    }
}
