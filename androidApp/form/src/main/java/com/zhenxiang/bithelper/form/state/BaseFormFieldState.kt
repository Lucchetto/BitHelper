package com.zhenxiang.bithelper.form.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class BaseFormFieldState<T, V>(
    initialValue: T,
    initialValidators: List<FormFieldValidator<V>>,
    initialTransformation: FormFieldValueTransformation<T>?
): FormFieldState<T, V> {

    private var _value by mutableStateOf(initialValue)
    override var value: T
        get() = _value
        set(value) {
            setValueInternal(value)
            _isValid = true
        }

    override var validators by mutableStateOf(initialValidators)

    override var transformation: FormFieldValueTransformation<T>? = initialTransformation
        set(value) {
            field = value
            setValueInternal(_value)
        }

    private var _isValid by mutableStateOf(true)
    override val isValid: Boolean
        get() = _isValid

    override fun validate(): Boolean {
        val currentValue = value
        validators.forEach {
            if (!runValidator(it, currentValue)) {
                _isValid = false
                return false
            }
        }
        _isValid = true
        return true
    }

    private fun setValueInternal(value: T) {
        transformation?.let {
            val transformedValue = it.transform(value)
            // Value shouldn't be updated if transformedValue is null
            if (transformedValue != null) {
                _value = transformedValue
            }
        } ?: run {
            _value = value
        }
    }

    protected abstract fun runValidator(validator: FormFieldValidator<V>, value: T): Boolean
}
