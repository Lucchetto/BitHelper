package com.zhenxiang.bithelper.form.state

class EnumFormFieldState<T: Enum<*>?>(
    initialValue: T,
    initialValidators: List<FormFieldValidator<T>> = listOf()
): BaseFormFieldState<T, T>(initialValue, initialValidators) {

    override fun runValidator(validator: FormFieldValidator<T>, value: T): Boolean =
        validator.validate(value)
}
