package com.example.shophai.ui.auth.validator

import com.example.shophai.R
import com.example.shophai.ui.auth.validator.base.BaseValidator
import com.example.shophai.ui.auth.validator.base.ValidateResult

/**
 * password validator
 * validating password with its
 * requirement
 */
class PasswordValidator(val password: String) : BaseValidator() {
    private val minPasswordLength = 6
    private val maxPasswordLength = 12

    override fun validate(): ValidateResult {
        if (password.length < minPasswordLength) {
            return ValidateResult(false, R.string.text_validation_error_min_pass_len)
        }
        if (password.length > maxPasswordLength) {
            return ValidateResult(false, R.string.text_validation_error_max_pass_len)
        }
        return ValidateResult(true, R.string.text_validation_success)
    }
}