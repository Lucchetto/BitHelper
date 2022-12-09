package com.zhenxiang.bithelper.form.state

import androidx.compose.ui.text.input.TextFieldValue

class TextFormFieldState(
    initialValue: TextFieldValue,
    initialValidators: List<FormFieldValidator<String>> = listOf(),
    initialTransformation: FormFieldValueTransformation<TextFieldValue>? = null
): BaseFormFieldState<TextFieldValue, String>(initialValue, initialValidators, initialTransformation) {

    constructor(
        initialValue: String = "",
        initialValidators: List<FormFieldValidator<String>> = listOf(),
        initialTransformation: FormFieldValueTransformation<TextFieldValue>? = null
    ): this(TextFieldValue(initialValue), initialValidators, initialTransformation)

    override fun runValidator(validator: FormFieldValidator<String>, value: TextFieldValue): Boolean =
        validator.validate(value.text)
}
