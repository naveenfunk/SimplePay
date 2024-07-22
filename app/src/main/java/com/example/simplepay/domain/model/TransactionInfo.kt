package com.example.simplepay.domain.model

import java.math.BigDecimal
import java.time.YearMonth

data class TransactionInfo(
    val amount: BigDecimal = BigDecimal.ZERO,
    val transactionType: TransactionType = TransactionType.PURCHASE,
    val cardInfo: CardInfo = CardInfo()
)
data class CardInfo(
    val cardPan: String = "",
    val expiryDate: YearMonth = YearMonth.now(),
    val securityCode: String = "",
)

enum class TransactionResult{
    APPROVED,
    DECLINED,
    CANCELLED,
    FAILURE,
}