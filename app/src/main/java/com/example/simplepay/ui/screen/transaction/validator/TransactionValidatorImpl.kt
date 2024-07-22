package com.example.simplepay.ui.screen.transaction.validator

import java.math.BigDecimal
import javax.inject.Inject

class TransactionValidatorImpl @Inject constructor(): TransactionValidator {

    override fun isAmountValid(amount: String): Boolean {
        try {
            if (amount.isNotEmpty()) BigDecimal(amount)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}