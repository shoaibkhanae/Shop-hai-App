package com.example.shophai.ui.auth.validator

import com.example.shophai.R
import com.example.shophai.ui.auth.validator.base.BaseValidator
import com.example.shophai.ui.auth.validator.base.ValidateResult


/**
 * for validating the
 * field is empty or not
 */
class EmptyValidator(val input: String) : BaseValidator() {
    override fun validate(): ValidateResult {
        val isValid = input.isNotEmpty()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_empty_field
        )
    }
}