package com.zhenxiang.bithelper.form.state

import androidx.compose.ui.text.input.TextFieldValue
import com.zhenxiang.bithelper.shared.utils.removeSpacesAndNewLines

interface FormFieldValueTransformation<T> {

    fun transform(value: T): T?

    companion object {

        val RemoveSpacesAndNewlines = object: FormFieldValueTransformation<TextFieldValue> {

            override fun transform(value: TextFieldValue) = value.copy(
                text = value.text.removeSpacesAndNewLines()
            )
        }
    }
}
