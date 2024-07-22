package com.example.simplepay.domain.model

import java.math.BigDecimal

data class TransactionInfo(
    val amount: BigDecimal,
    val transactionType: TransactionType,
    val cardInfo: CardInfo,
    val result: TransactionResult,
)

data class CardInfo(
    val cardPan: String,
    val expiryMonth: Int,
    val expiryYear: Int,
    val securityCode: String,
)

enum class TransactionResult {
    APPROVED,
    DECLINED,
    CANCELLED,
    FAILURE,
}