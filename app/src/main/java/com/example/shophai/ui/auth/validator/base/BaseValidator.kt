package com.example.shophai.ui.auth.validator.base

import com.example.shophai.R

/**
 * Base validator of validating
 * user input of different types
 */
abstract class BaseValidator : IValidator {
    companion object {
        fun validate(vararg validators: IValidator): ValidateResult {
            validators.forEach {
                val result = it.validate()
                if (!result.isSuccess)
                    return result
            }
            return ValidateResult(true, R.string.text_validation_success)
        }
    }
}