package com.zhenxiang.bithelper.form.state

val <T, V> BaseFormFieldState<T, V>.isNotValid: Boolean
    get() = !isValid
