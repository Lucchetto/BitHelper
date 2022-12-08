package com.zhenxiang.bithelper.form.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class BaseFormFieldState<T, V>(
    initialValue: T,
    initialValidators: List<FormFieldValidator<V>>
): FormFieldState<T, V> {

    private var _value by mutableStateOf(initialValue)
    override var value: T
    get() = _value
    set(value) {
        _value = value
        _isValid = true
    }

    override var validators by mutableStateOf(initialValidators)

    private var _isValid by mutableStateOf(true)
    override val isValid: Boolean
        get() = _isValid

    override fun validate(): Boolean {
        val currentValue = _value
        validators.forEach {
            if (!runValidator(it, currentValue)) {
                _isValid = false
                return false
            }
        }
        _isValid = true
        return true
    }

    protected abstract fun runValidator(validator: FormFieldValidator<V>, value: T): Boolean
}
