package com.zhenxiang.bithelper.form.state

class StringFormFieldState(
    initialValue: String = "",
    initialValidators: List<FormFieldValidator<String>> = listOf(),
    initialTransformation: FormFieldValueTransformation<String>? = null
): BaseFormFieldState<String, String>(initialValue, initialValidators, initialTransformation) {

    override fun runValidator(validator: FormFieldValidator<String>, value: String): Boolean =
        validator.validate(value)
}
