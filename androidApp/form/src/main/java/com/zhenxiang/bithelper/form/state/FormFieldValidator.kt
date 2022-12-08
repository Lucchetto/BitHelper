package com.zhenxiang.bithelper.form.state

interface FormFieldValidator<in T> {

    fun validate(value: T): Boolean

    companion object {

        val Required = object: FormFieldValidator<Any?> {

            override fun validate(value: Any?) = if (value is CharSequence) {
                value.isNotBlank()
            } else {
                value != null
            }
        }
    }
}
