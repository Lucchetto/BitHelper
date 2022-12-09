package com.zhenxiang.bithelper.form

import androidx.compose.ui.text.input.TextFieldValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DecimalStringTransformationTest {

    @Test(expected = IllegalArgumentException::class)
    fun `given negative precision Then IllegalArgumentException is thrown`() {
        DecimalStringTransformation(-10).transform(TextFieldValue(text = "0.5"))
    }

    @Test
    fun `when transform invoked on non decimal string Then returns null`() {
        assertNull(
            DecimalStringTransformation(7).transform(TextFieldValue(text = "ashdjvna"))
        )
    }

    @Test
    fun `given precision zero When transform invoked on decimal string Then returns integer part`() {
        assertEquals(
            "69",
            DecimalStringTransformation(0).transform(TextFieldValue(text = "69.420"))?.text
        )
    }

    @Test
    fun `when transform invoked on integer string Then returns same string`() {
        val integer1 = "25023"
        val integer2 = "25023."

        DecimalStringTransformation(8).apply {
            assertEquals(integer1, TextFieldValue(text = integer1).text)
            assertEquals(integer2, TextFieldValue(text = integer2).text)
        }
    }

    @Test
    fun `when transform invoked on decimal string with spaces Then returns same trimmed decimal string`() {
            assertEquals(
                "25023.100",
                DecimalStringTransformation(7).transform(TextFieldValue(text = " 25023.100 "))?.text
            )
    }

    @Test
    fun `when transform invoked on decimal string with exceeding precision Then returns decimal string with trimmed precision`() {
        assertEquals(
            "25023.1000001",
            DecimalStringTransformation(7).transform(TextFieldValue(text = "25023.100000105"))?.text
        )
    }
}
