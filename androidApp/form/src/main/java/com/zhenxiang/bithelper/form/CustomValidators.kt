package com.zhenxiang.bithelper.form

import com.dsc.form_builder.Validators

object CustomValidators {

    fun notBlank(message: String = ValidationMessages.REQUIRED) = Validators.Custom(message) {
        (it as String).isNotBlank()
    }
}
