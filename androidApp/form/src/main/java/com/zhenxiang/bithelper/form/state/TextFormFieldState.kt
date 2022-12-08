package com.zhenxiang.bithelper.form.state

import androidx.compose.ui.text.input.TextFieldValue

class TextFormFieldState(
    initialValue: TextFieldValue,
    initialValidators: List<FormFieldValidator<String>> = listOf()
): BaseFormFieldState<TextFieldValue, String>(initialValue, initialValidators) {

    constructor(
        initialValue: String = "",
        initialValidators: List<FormFieldValidator<String>> = listOf()
    ): this(TextFieldValue(initialValue), initialValidators)

    override fun runValidator(validator: FormFieldValidator<String>, value: TextFieldValue): Boolean =
        validator.validate(value.text)
}
