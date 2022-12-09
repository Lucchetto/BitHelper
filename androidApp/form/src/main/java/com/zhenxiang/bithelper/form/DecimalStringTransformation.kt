package com.zhenxiang.bithelper.form

import androidx.compose.ui.text.input.TextFieldValue
import com.zhenxiang.bithelper.form.state.FormFieldValueTransformation
import com.zhenxiang.bithelper.shared.utils.removeSpacesAndNewLines

class DecimalStringTransformation(val precision: Int): FormFieldValueTransformation<TextFieldValue> {

    init {
        if (precision < 0) {
            throw IllegalArgumentException("Precision must ge greater than 0 !")
        }
    }

    override fun transform(value: TextFieldValue): TextFieldValue? {
        val trimmedInput = value.text
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

        return value.copy(
            text = if (decimalPart == null || precision == 0) {
                integerPart
            } else {
                "$integerPart$DECIMAL_POINT_SYMBOL$decimalPart"
            }
        )
    }

    companion object {
        private const val DECIMAL_POINT_SYMBOL: Char = '.'
        private const val ALT_DECIMAL_POINT_SYMBOL: Char = ','

        private val DECIMAL_REGEX = "^\\d*\\$DECIMAL_POINT_SYMBOL?\\d*\$".toRegex()
    }
}
