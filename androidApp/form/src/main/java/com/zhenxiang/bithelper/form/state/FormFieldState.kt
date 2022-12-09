package com.zhenxiang.bithelper.form.state

interface FormFieldState<T, V> {

    var value: T

    var validators: List<FormFieldValidator<V>>

    var transformation: FormFieldValueTransformation<T>?

    /**
     * Returns the latest validation state, make sure to invoke [validate] first to have the updated value
     */
    val isValid: Boolean

    fun validate(): Boolean
}