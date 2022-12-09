package com.zhenxiang.bithelper.form

import com.zhenxiang.bithelper.shared.utils.removeSpacesAndNewLines

object DecimalStringTransform {

    private const val DECIMAL_POINT_SYMBOL: Char = '.'
    private const val ALT_DECIMAL_POINT_SYMBOL: Char = ','

    private val DECIMAL_REGEX = "^\\d*\\$DECIMAL_POINT_SYMBOL?\\d*\$".toRegex()

    fun transform(input: String, precision: Int): String? {
        if (precision < 0) {
            throw IllegalArgumentException("Precision must ge greater than 0 !")
        }

        val trimmedInput = input
            .removeSpacesAndNewLines()
            .replace(ALT_DECIMAL_POINT_SYMBOL, DECIMAL_POINT_SYMBOL)

        // No point going ahead if it doesn't match a decimal number
        if (!DECIMAL_REGEX.matches(trimmedInput)) {
            return null
        }

        val split = trimmedInput.split(DECIMAL_POINT_SYMBOL)
        val integerPart = split[0]
        val decimalPart = split.getOrNull(1)?.let {
            if (it.length > precision) {
                // Remove ending decimal digits to fit allowed precision
                it.substring(0, precision)
            } else {
                it
            }
        }

        return if (decimalPart == null || precision == 0) {
            integerPart
        } else {
            "$integerPart$DECIMAL_POINT_SYMBOL$decimalPart"
        }
    }
}
