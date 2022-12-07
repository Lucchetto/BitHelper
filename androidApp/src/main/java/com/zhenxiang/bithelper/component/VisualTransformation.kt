package com.zhenxiang.bithelper.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

class SuffixTransformation(suffix: String, private val style: SpanStyle): VisualTransformation {

    private val suffix = "    $suffix"

    override fun filter(text: AnnotatedString) = TransformedText(
        buildAnnotatedString {
            append(text)

            withStyle(style) {
                append(suffix)
            }
        },
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = offset
            override fun transformedToOriginal(offset: Int): Int = offset.coerceAtMost(text.length)
        }
    )
}
