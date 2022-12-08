package com.zhenxiang.bithelper.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dsc.form_builder.BaseState
import com.dsc.form_builder.Validators

/**
 * This class represents the state of a single choice form field with a generic data type [T].
 * It overrides members from [BaseState].
 *
 * @param name The name of the field used to access the state when required in the form
 * @param initial The initial value/state of the field.
 * @param validators This is the list of [Validators] that are used to validate the field state. By default the field states will have an empty list. You can override this and provide your own list of validators.
 *
 */
class TypedChoiceState<T>(
    initial: T?,
    name: String,
    validators: List<Validators> = listOf(),
): BaseState<T?>(initial, name, null, validators) {

    override var value: T? by mutableStateOf(initial)

    // TODO: implement other validators
    override fun validate(): Boolean {
        val validations = validators.map {
            when (it) {
                is Validators.Required -> validateRequired(it.message)
                else -> true
            }
        }
        return validations.all { it }
    }

    fun change(update: T) {
        hideError()
        this.value = update
    }

    /**
     * This function validates a required field.
     * It will return true if the value is not empty.
     * @param message the error message passed to [showError] to display if the [value] is empty. By default we use the [REQUIRED_MESSAGE] constant.
     */
    private fun validateRequired(message: String): Boolean {
        val valid = value != null
        if (!valid) showError(message)
        return valid
    }

}