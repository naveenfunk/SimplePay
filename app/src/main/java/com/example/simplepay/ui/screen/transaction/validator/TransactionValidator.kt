package com.example.simplepay.ui.screen.transaction.validator

interface TransactionValidator {
    fun isAmountValid(amount: String): Boolean
}