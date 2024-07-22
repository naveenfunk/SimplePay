package com.example.simplepay.ui.screen.transaction.validator

interface TransactionValidator {
    fun isAmountValid(amount: String): Boolean
    fun isCardPanValid(pan: String): Boolean
    fun isCardExpiryMonthValid(month: String): Boolean
    fun isCardExpiryYearValid(year: String): Boolean
    fun isCardSecurityCodeValid(code: String): Boolean
}