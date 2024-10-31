package com.example.shophai.ui.auth.validator.base

/**
 * For validating result
 * success or error
 * if error every error
 * contain message
 */
data class ValidateResult(
    val isSuccess: Boolean,
    val message: Int
)
