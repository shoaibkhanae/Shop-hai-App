package com.example.shophai.ui.auth.validator.base


/**
 * it is a validator interface
 * it has validate method
 * every validator class will
 * implement in its own way
 */
interface IValidator {
    fun validate(): ValidateResult
}