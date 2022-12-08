package com.zhenxiang.bithelper.form.state

class StringFormFieldState(
    initialValue: String = "",
    initialValidators: List<FormFieldValidator<String>> = listOf()
): BaseFormFieldState<String, String>(initialValue, initialValidators) {

    override fun runValidator(validator: FormFieldValidator<String>, value: String): Boolean =
        validator.validate(value)
}
