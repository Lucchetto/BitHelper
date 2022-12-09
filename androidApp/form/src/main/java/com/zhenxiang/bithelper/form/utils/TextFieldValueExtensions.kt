package com.zhenxiang.bithelper.form.utils

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

/**
 * Copy [TextFieldValue] with a new text while preserving the current
 * [TextFieldValue.selection] and [TextFieldValue.composition]
 */
internal fun TextFieldValue.copyPreserveTextRange(newText: String): TextFieldValue {
    val diffLength = text.length - newText.length
    return copy(
        text = newText,
        selection = TextRange(
            (selection.start - diffLength),
            selection.end - diffLength
        ),
        composition = composition?.let {
            TextRange(0, it.end - diffLength)
        }
    )
}
