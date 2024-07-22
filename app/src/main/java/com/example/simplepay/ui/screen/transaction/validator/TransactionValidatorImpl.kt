package com.example.simplepay.ui.screen.transaction.validator

import java.math.BigDecimal
import java.time.Year
import javax.inject.Inject

class TransactionValidatorImpl @Inject constructor() : TransactionValidator {

    private val cardPanLengthRange = 13..19
    private val cardMonthRange = 1..12

    override fun isAmountValid(amount: String): Boolean {
        try {
            if (amount.isNotEmpty()) BigDecimal(amount)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    override fun isCardPanValid(pan: String): Boolean {
        return pan.length in cardPanLengthRange
    }

    override fun isCardExpiryMonthValid(month: String): Boolean {
        return month.toInt() in cardMonthRange
    }

    override fun isCardExpiryYearValid(year: String): Boolean {
        return year.toInt() >= Year.now().value
    }

    override fun isCardSecurityCodeValid(code: String): Boolean {
        return code.length == 3
    }
}