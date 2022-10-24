package com.zhenxiang.dcaboy.android.form

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState

fun FormState<TextFieldState>.getValueOfTextField(name: String): String = fields
    .firstOrNull { name == it.name }?.value ?: throw IllegalArgumentException("Cannot find form field with name $name")
